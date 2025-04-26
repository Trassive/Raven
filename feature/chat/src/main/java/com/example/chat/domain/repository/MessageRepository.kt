package com.example.chat.domain.repository

import com.example.chat.data.model.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {
    suspend fun getMessages(chatId: String, userId: String): Flow<Message>
    suspend fun disconnect()
    suspend fun sendMessage(chatId: String, message: Message)
}