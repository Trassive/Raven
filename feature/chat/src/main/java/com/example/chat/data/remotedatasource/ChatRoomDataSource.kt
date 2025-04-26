package com.example.chat.data.remotedatasource

import com.example.chat.data.model.ChatRoomModel
import com.example.chat.di.REST_CLIENT
import com.example.chat.di.REST_URL_NAME
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject
import javax.inject.Named

class ChatRoomDataSource @Inject constructor(
    @Named(REST_CLIENT) private val httpClient: HttpClient,
    @Named(REST_URL_NAME) private val url: String
) {
    suspend fun getChatRoom(id: String): ChatRoomModel {
        return httpClient.get(url.format(id)).body()
    }
}