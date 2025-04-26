package com.example.chat.ui.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chat.ui.model.ChatMessage
import com.example.chat.ui.model.MessageContent

@Composable
fun MessageList(
    padding: PaddingValues,
    messages: List<ChatMessage> = emptyList()
) {
    Box(
        Modifier.fillMaxWidth()
        .padding(padding)
    ){
        Row(
            Modifier.fillMaxWidth()
            .padding(16.dp)
        ){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxSize()
            ){
                items(messages){ message->
                    MessageItem(message)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MessageListPreview() {
    MessageList(
        padding = PaddingValues(16.dp),
        messages = getFakeMessages()
    )
}

fun getFakeMessages(): List<ChatMessage> {
    return listOf(
        ChatMessage(
            id = "1",
            senderName = "Alice",
            avatar = "https://example.com/avatar1.jpg",
            message = MessageContent.Text("Hello, how are you?"),
            timeStamp = "10:00 AM",
            isMine = false
        ),
        ChatMessage(
            id = "2",
            senderName = "Me",
            avatar = "https://example.com/avatar2.jpg",
            message = MessageContent.Text("I'm good, thanks!"),
            timeStamp = "10:01 AM",
            isMine = true
        ),
        ChatMessage(
            id = "3",
            senderName = "Alice",
            avatar = "https://example.com/avatar1.jpg",
            message = MessageContent.Text("Great to hear!"),
            timeStamp = "10:02 AM",
            isMine = false
        )
    )
}