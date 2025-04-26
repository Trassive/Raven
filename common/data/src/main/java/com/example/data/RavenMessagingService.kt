package com.example.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.example.framework.navigation.deepLink
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class RavenMessagingService: FirebaseMessagingService(){
    companion object{
        const val CHANNEL_ID = "Chat_message"
        const val CHANNEL_DESCRIPTION = "Receive a notification when a chat message is received "
        const val CHANNEL_TITLE = "New chat message notification"
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        //TODO
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        if(remoteMessage.data.isNotEmpty()){

            val senderName = remoteMessage.data["senderName"]
            val chatId = remoteMessage.data["chatId"]
            val message = remoteMessage.data["message"]
            val messageId = remoteMessage.data["messageId"]
            if(chatId!=null&&messageId!=null){
                showNotification(senderName,chatId,message,messageId)
            }
        }
    }

    private fun showNotification(senderName: String?, chatId: String, message: String?, messageId: String) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(android.os.Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_TITLE,NotificationManager.IMPORTANCE_DEFAULT)
                .apply { description = CHANNEL_DESCRIPTION }
            notificationManager.createNotificationChannel(channel)
        }
        val deepLink = deepLink.replace("{chatId}",chatId)
        val intent = Intent(
                Intent.ACTION_VIEW,
                deepLink.toUri()
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(senderName)
            .setContentText(message)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(messageId.toInt(),notification)
    }


}