package com.example.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Message::class, Conversation::class], version = 1)
abstract class ChatAppDatabase: RoomDatabase() {
    abstract fun getMessagesDao(): MessageDao
    abstract fun getConversationsDao(): ConversationDao

    companion object{
        private const val DATABASE_NAME = "chat_app_database"
        @Volatile
        private var INSTANCE: ChatAppDatabase? = null

        fun getDatabase(context: Context): ChatAppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ChatAppDatabase::class.java,
                    DATABASE_NAME
                ).build().also {
                        INSTANCE = it
                    }
            }
        }
    }
}