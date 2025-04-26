package com.example.chat.data.localdatasource

import com.example.data.database.Message
import com.example.data.database.MessageDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MessagesLocalDataSource @Inject constructor(
    private val messagesDao: MessageDao
) {
    fun getMessagesForConversation(conversationId: String): Flow<List<Message>> {
        return messagesDao.getMessagesForConversation(conversationId)
    }
    suspend fun insertMessage(message: Message) {
        messagesDao.insertMessage(message)
    }
    suspend fun deleteMessage(message: Message) {
        messagesDao.deleteMessage(message)
    }
}