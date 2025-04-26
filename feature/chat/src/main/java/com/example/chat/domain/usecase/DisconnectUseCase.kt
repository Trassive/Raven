package com.example.chat.domain.usecase

import com.example.chat.domain.repository.MessageRepository
import javax.inject.Inject

class DisconnectUseCase @Inject constructor(
    private val messageRepository: MessageRepository
) {
    suspend operator fun invoke() {
        messageRepository.disconnect()
    }
}
