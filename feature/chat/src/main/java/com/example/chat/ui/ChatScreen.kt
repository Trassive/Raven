package com.example.chat.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.chat.ui.util.components.MessageBox
import com.example.chat.ui.util.components.MessageList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    chatViewModel: ChatViewModel,
    chatId: String,
    onBack: ()-> Unit
){
    val messages by chatViewModel.messages.collectAsState()
    val uiState by chatViewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        chatViewModel.loadChatRoom(chatId.orEmpty())
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Chat")
                 },
            )
        },
        bottomBar = {
            MessageBox(
                onSend = {message ->
                    chatViewModel.sendMessage(message)
                }
            )
        }
    ){innerPadding ->
        MessageList(
            padding = innerPadding,
            messages = messages
        )
    }
}

