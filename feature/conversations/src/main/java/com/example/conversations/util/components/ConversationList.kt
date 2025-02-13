package com.example.conversations.util.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.conversations.model.Conversation

@Composable
fun ConversationList(list: List<Conversation>, onChatClick: (Int) -> Unit) {
    LazyColumn {
       items(list) { conversation ->
           Conversation(
                conversation = conversation,
                onChatComposable = { onChatClick(conversation.id) }
           )
       }
    }
}
@Preview(showBackground = true)
@Composable
fun ConversationListPreview() {
    ConversationList(
        list = listOf(
            Conversation(
                id = 1,
                name = "John Doe",
                messsage = "Hello",
                timeStamp = "12:00",
                unreadCount = 1,
                avatar = "https://example.com/avatar.jpg"
            )
        ),
        onChatClick = {}
    )
}