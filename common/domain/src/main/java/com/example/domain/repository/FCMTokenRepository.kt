package com.example.domain.repository

interface FCMTokenRepository {
    suspend fun getFCMToken(): String?
}