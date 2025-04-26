package com.example.chat.domain.repository

import com.example.chat.data.model.ChatRoom

interface ChatRoomRepository {
    suspend fun getInitialChatRoom(id: String): ChatRoom
}