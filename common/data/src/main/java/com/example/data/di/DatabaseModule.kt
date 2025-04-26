package com.example.data.di

import android.content.Context
import com.example.data.database.ChatAppDatabase
import com.example.data.database.ConversationDao
import com.example.data.database.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ChatAppDatabase {
        return ChatAppDatabase.getDatabase(context)
    }

    @Provides
    fun provideConversationDao(database: ChatAppDatabase): ConversationDao {
        return database.getConversationsDao()
    }

    @Provides
    fun provideMessageDao(database: ChatAppDatabase): MessageDao {
        return database.getMessagesDao()
    }
}