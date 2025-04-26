package com.example.chat.domain.usecase

import com.example.chat.data.model.Message
import com.example.chat.domain.repository.MessageRepository
import javax.inject.Inject

class SendMessagesUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke(chatId: String, message: Message) {
        messageRepository.sendMessage(chatId, message)
    }
}