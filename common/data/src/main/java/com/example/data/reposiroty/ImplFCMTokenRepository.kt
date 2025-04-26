package com.example.data.reposiroty

import com.example.data.datasource.FCMTokenDataSource
import com.example.domain.repository.FCMTokenRepository
import javax.inject.Inject

class ImplFCMTokenRepository @Inject constructor(
    private val fcmTokenDataSource: FCMTokenDataSource,
): FCMTokenRepository {
    override suspend fun getFCMToken(): String? {
        return fcmTokenDataSource.getFCMToken()
    }

}
