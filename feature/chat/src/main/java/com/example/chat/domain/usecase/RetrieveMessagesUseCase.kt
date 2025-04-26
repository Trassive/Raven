package com.example.chat.domain.usecase

import com.example.chat.data.model.Message
import com.example.chat.domain.repository.MessageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RetrieveMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(chatId: String, userId: String): Flow<Message> {
        return messageRepository.getMessages(chatId = chatId, userId = userId)
    }
}