package com.example.chat.data.remotedatasource

import android.util.Log
import com.example.chat.data.model.Message
import com.example.chat.data.model.WebSocketMessageModel
import com.example.chat.di.WS_CLIENT
import com.example.chat.di.WS_URL_NAME
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.converter
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.serialization.deserialize
import io.ktor.serialization.serialize
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.retryWhen
import okio.IOException
import javax.inject.Inject
import javax.inject.Named

class MessageDataSource @Inject constructor(
    @Named(WS_CLIENT) private val httpClient: HttpClient,
    @Named(WS_URL_NAME) private val url: String
) {
    private lateinit var webSocketSession: DefaultClientWebSocketSession
    fun connect(): Flow<Message> {
        return flow{
            try{
                httpClient.webSocketSession { url(this@MessageDataSource.url) }
                    .apply { webSocketSession = this }
                    .incoming
                    .receiveAsFlow()
                    .map { frame ->
                        try{
                            webSocketSession.handleFrame(frame)
                                ?.toDomain()
                                ?.let { message ->
                                    emit(message)
                                }
                        }catch (e: Exception){
                            Log.d(TAG, "WebSocket frame handling error",e)
                        }
                    }

            }catch (e: IOException){
                Log.d(TAG, "WebSocket connection error",e)
                throw e
            }
        }.retryWhen{cause,attempt->
            cause is IOException && attempt < MAX_ATTEMPTS
            delay(RECONNECT_DELAY pow attempt)
            true
        }.catch { e ->
            Log.d(TAG, "WebSocket connection error",e)
        }
    }
    suspend fun send(message: Message){
        val websocketMessage = WebSocketMessageModel.fromDomain(message)
        webSocketSession.converter?.serialize(websocketMessage)?.let {
            webSocketSession.send(it)
        }
    }
    suspend fun disconnect(){
        webSocketSession.close()
    }
    private  suspend fun DefaultClientWebSocketSession.handleFrame(frame: Frame): WebSocketMessageModel? {
        return when(frame){
            is Frame.Text -> {
                converter?.deserialize(frame)
            }
            is Frame.Close -> {
                disconnect()
                null
            }
            else -> null
        }
    }

    companion object {
        private const val TAG = "MessageDataSource"
        private const val MAX_ATTEMPTS = 5
        private const val RECONNECT_DELAY = 3000L

        infix fun Long.pow(x: Long): Long= this.pow(x)
    }
}