package com.example.chat.ui.model

data class ChatMessage(
    val id: String,
    val senderName: String,
    val avatar: String,
    val timeStamp: String,
    val isMine: Boolean,
    val message: MessageContent
)
sealed interface MessageContent{
    data class Text(val text: String): MessageContent
    data class Image(val url: String): MessageContent
    data class Video(val url: String): MessageContent
    data class Audio(val url: String): MessageContent
}
