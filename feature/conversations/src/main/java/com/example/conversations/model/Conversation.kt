package com.example.conversations.model


data class Conversation(
    val id: String,
    val name: String,
    val messsage: String,
    val timeStamp: String,
    val unreadCount: Int,
    val avatar: String
)