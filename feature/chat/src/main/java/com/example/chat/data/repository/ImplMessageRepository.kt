package com.example.chat.data.repository

import com.example.chat.data.localdatasource.MessagesLocalDataSource
import com.example.chat.data.model.Message
import com.example.chat.data.remotedatasource.FirestoreMessagesDataSource
import com.example.chat.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImplMessageRepository @Inject constructor(
    private val remoteSource: FirestoreMessagesDataSource,
    private val localSource: MessagesLocalDataSource
): MessageRepository {
    override suspend fun getMessages(chatId: String, userId: String): Flow<Message> {
        return remoteSource.getMessages(chatId, userId)
    }

    override suspend fun disconnect() {
//        messageDataSource.disconnect()

    }

    override suspend fun sendMessage(chatId: String, message: Message) {
        remoteSource.sendMessage(chatId, message)
    }
}