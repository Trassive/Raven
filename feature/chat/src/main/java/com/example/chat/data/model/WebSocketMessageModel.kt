package com.example.chat.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WebSocketMessageModel(
    val id: String,
    val message: String,
    val senderName: String,
    val senderAvatar: String,
    val timestamp: String,
    val isMine: Boolean,
    val messageType: String,
    val messageDescription: String
){
    companion object {
        const val TYPE_TEXT = "Text"
        const val TYPE_IMAGE = "Image"
        fun fromDomain(message: Message): WebSocketMessageModel {
            return WebSocketMessageModel(
                id = message.id,
                senderName = message.senderName,
                senderAvatar = message.senderAvatar,
                timestamp = message.timeStamp,
                isMine = message.isMine,
                message = message.message,
                messageType = fromContentType(message.messageType),
                messageDescription = message.messageDescription
            )
        }
        private fun fromContentType(messageType: Message.ContentType) = when (messageType) {
            Message.ContentType.Text -> TYPE_TEXT
            Message.ContentType.Image -> TYPE_IMAGE
        }
    }
    fun toDomain(): Message {
        return Message(
            id = id,
            senderName = senderName,
            senderAvatar = senderAvatar,
            timeStamp = timestamp,
            isMine = isMine,
            message = message,
            messageType = toContentType(),
            messageDescription = messageDescription
        )
    }


    private fun toContentType() = when (messageType) {
        TYPE_TEXT -> Message.ContentType.Text
        TYPE_IMAGE -> Message.ContentType.Image
        else -> Message.ContentType.Text
    }
}