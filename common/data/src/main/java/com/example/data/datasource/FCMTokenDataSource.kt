package com.example.data.datasource

import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FCMTokenDataSource @Inject constructor (
    private val firebaseMessaging: FirebaseMessaging
){
    suspend fun getFCMToken(): String? = try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            null
        }

}