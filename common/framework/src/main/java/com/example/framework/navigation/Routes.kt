package com.example.framework.navigation

import kotlinx.serialization.Serializable

const val uri = "raven://raven.app"

sealed interface Routes{
    @Serializable
    data object ConversationList: Routes
    data object NewChat: Routes
    data class Chat(val chatId: Int): Routes
}