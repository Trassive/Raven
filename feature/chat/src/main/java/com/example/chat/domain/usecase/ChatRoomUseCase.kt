package com.example.chat.domain.usecase

import com.example.chat.data.model.ChatRoom
import com.example.chat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class ChatRoomUseCase @Inject constructor(
    private val chatRoomRepository: ChatRoomRepository
) {
    suspend operator fun invoke(id: String): ChatRoom {
        return chatRoomRepository.getInitialChatRoom(id)
    }
}
