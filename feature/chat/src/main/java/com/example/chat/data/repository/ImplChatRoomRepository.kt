package com.example.chat.data.repository

import com.example.chat.data.model.ChatRoom
import com.example.chat.data.remotedatasource.ChatRoomDataSource
import com.example.chat.domain.repository.ChatRoomRepository
import javax.inject.Inject

class ImplChatRoomRepository @Inject constructor(
    private val chatRoomDataSource: ChatRoomDataSource
): ChatRoomRepository {
    override suspend fun getInitialChatRoom(id: String): ChatRoom {
        return chatRoomDataSource.getChatRoom(id).toDomain()
    }
}