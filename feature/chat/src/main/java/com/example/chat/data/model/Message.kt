package com.example.chat.data.model

data class Message(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val timeStamp: String,
    val isMine: Boolean,
    val message: String,
    val messageType: ContentType,
    val messageDescription: String
){
    enum class ContentType{
        Text,
        Image,

    }
}
