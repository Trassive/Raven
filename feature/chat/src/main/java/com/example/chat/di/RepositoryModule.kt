package com.example.chat.di

import com.example.chat.data.repository.ImplChatRoomRepository
import com.example.chat.data.repository.ImplMessageRepository
import com.example.chat.domain.repository.ChatRoomRepository
import com.example.chat.domain.repository.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesMessageRepository(
        messageRepository: ImplMessageRepository
    ): MessageRepository


    @Binds
    abstract fun providesChatRoomRepository(
        chatRoomRepository: ImplChatRoomRepository
    ): ChatRoomRepository
}