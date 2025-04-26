package com.example.chat.domain.repository

interface BackupRepository {
    suspend fun backupAllMessages()
}