package com.example.domain.repository

interface InternalTokenRepository {
    suspend fun storeToken(userId: String, token: String)
}