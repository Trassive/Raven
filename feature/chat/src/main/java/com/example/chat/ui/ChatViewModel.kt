package com.example.chat.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chat.data.model.ChatRoom
import com.example.chat.data.model.Message
import com.example.chat.domain.usecase.ChatRoomUseCase
import com.example.chat.domain.usecase.DisconnectUseCase
import com.example.chat.domain.usecase.RetrieveMessagesUseCase
import com.example.chat.domain.usecase.SendMessagesUseCase
import com.example.chat.ui.model.Chat
import com.example.chat.ui.model.ChatMessage
import com.example.chat.ui.model.MessageContent
import com.example.domain.user.GetUserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.chat.data.model.Message as DomainMessage

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val retrieveMessages: RetrieveMessagesUseCase,
    private val sendMessages: SendMessagesUseCase,
    private val disconnect: DisconnectUseCase,
    private val chatRoomUseCase: ChatRoomUseCase,
    private val getUserData: GetUserData
): ViewModel() {
    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _uiState = MutableStateFlow(Chat())
    val uiState: StateFlow<Chat> = _uiState.asStateFlow()

//    private var job: Job? = null

    private lateinit var chatRoom: ChatRoom
    fun loadAndUpdateMessages(){
        viewModelScope.launch(Dispatchers.IO){
            retrieveMessages(userId = getUserData.getData().id, chatId = chatRoom.id).map {
                it.toUi()
            }.collect{
                withContext(Dispatchers.Main){
                    _messages.value += it
                }
            }
        }
    }
    fun sendMessage(messageText: String){
        viewModelScope.launch(Dispatchers.IO){
            val user = getUserData.getData()
            val message = DomainMessage(
                id = chatRoom.id,
                senderAvatar = user.avatar,
                senderName = user.name,
                isMine = true,
                messageType = DomainMessage.ContentType.Text,
                message = messageText,
                messageDescription = messageText,
                timeStamp = System.currentTimeMillis().toString()
            )
            sendMessages(chatId = chatRoom.id, message = message)
        }
    }
    fun loadChatRoom(id: String){
        viewModelScope.launch(Dispatchers.IO){
            chatRoom = chatRoomUseCase(id)
            withContext(Dispatchers.Main){
                _uiState.value = chatRoom.toUi()
                _messages.value = chatRoom.lastMessages.map { it.toUi() }
                loadAndUpdateMessages()
            }

        }
    }
    override fun onCleared() {
        runBlocking(Dispatchers.IO){ disconnect() }

        super.onCleared()
    }
    private fun DomainMessage.toUi():ChatMessage{
        return ChatMessage(
            id = id,
            senderName = senderName,
            avatar = senderAvatar,
            timeStamp = timeStamp,
            isMine = isMine,
            message = messageContent()
        )
    }
    private fun ChatRoom.toUi() = run {
        Chat(
            id = id,
            name = senderName,
            avatar = senderAvatar
        )
    }
    private fun Message.messageContent() = when (this.messageType) {
        Message.ContentType.Text -> MessageContent.Text(this.message)
        Message.ContentType.Image -> MessageContent.Image(this.message)
    }


}

