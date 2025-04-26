package com.example.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAndStoreFCMToken @Inject constructor(
    private val fcmRepository: FCMTokenRepository,
    private val internalTokenRepository: InternalTokenRepository
) {
    suspend operator fun invoke(userId: String) = withContext(Dispatchers.IO) {
        val token = fcmRepository.getFCMToken()
        internalTokenRepository.storeToken(userId, token!!)
    }
}