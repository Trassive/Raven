package com.example.framework.navigation

import kotlinx.serialization.Serializable

const val basePath = "raven://raven.app"
val deepLink = "$basePath/chat/{chatId}"

sealed interface Routes{
    @Serializable
    data object ConversationList: Routes
    data object NewChat: Routes
    data class Chat(val chatId: String): Routes
}