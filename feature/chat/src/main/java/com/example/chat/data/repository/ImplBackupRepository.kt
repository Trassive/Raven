package com.example.chat.data.repository

import com.example.chat.data.remotedatasource.StorageRemoteDataSource
import com.example.chat.domain.repository.BackupRepository
import com.example.data.database.ConversationDao
import com.example.data.database.MessageDao
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import java.io.File
import javax.inject.Inject

class ImplBackupRepository @Inject constructor(
    private val messageDao: MessageDao,
    private val conversationDao: ConversationDao,
    private val storageRemoteDataSource: StorageRemoteDataSource,

    ): BackupRepository {
    override suspend fun backupAllMessages() {
        val conversations = conversationDao.getConversations().first()
        conversations.forEach { conversation ->
            val messages = messageDao.getMessagesForConversation(conversation.id)
            val json = Json.encodeToJsonElement(messages)
            val tempFile = File.createTempFile("messages_$conversation.id",".json",File(System.getProperty("java.io.tmpdir")!!))
            tempFile.writeText(json.toString())
            storageRemoteDataSource.uploadFile(tempFile, "conversation/${conversation.id}/message.json")
            tempFile.delete()
        }
    }

}