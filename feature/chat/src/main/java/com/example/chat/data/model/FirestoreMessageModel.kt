package com.example.chat.data.model

import android.icu.text.SimpleDateFormat
import com.google.firebase.Timestamp
import com.google.firebase.firestore.PropertyName
import java.util.Locale

data class FirestoreMessageModel(
    @Transient
    val id: String = "",
    @get:PropertyName("senderId")
    @set:PropertyName("senderId")
    var senderId: String = "",
    @get:PropertyName("senderName")
    @set:PropertyName("senderName")
    var senderName: String = "",
    @get:PropertyName("senderAvatar")
    @set:PropertyName("senderAvatar")
    var senderAvatar: String = "",
    @get:PropertyName("content")
    @set:PropertyName("content")
    var content: String = "",
    @get:PropertyName("timestamp")
    @set:PropertyName("timestamp")
    var timestamp: Timestamp = Timestamp.now()
){
    companion object {

        fun fromDomain(message: Message): FirestoreMessageModel
        {
            return FirestoreMessageModel(
                id = "",
                senderName = message.senderName,
                senderAvatar = message.senderAvatar,
                content = message.message
            )
        }
    }
    fun toDomain(userId: String): Message {
        return Message(
            id = id,
            senderName = senderName,
            senderAvatar = senderAvatar,
            timeStamp = timestamp.toDateString(),
            isMine = senderId == userId,
            message = content,
            messageType = Message.ContentType.Text,
            messageDescription = ""
        )
    }
    private fun Timestamp.toDateString(): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
        val date = this.toDate()
        return formatter.format(date)
    }
}