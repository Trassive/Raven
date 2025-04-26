package com.example.chat.ui.util.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageBox(
    onSend: (String) -> Unit
){
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier.defaultMinSize()
            .padding(top = 0.dp, start = 16.dp,
                end = 16.dp,
                bottom = 16.dp)
            .fillMaxWidth()
    ){
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth(0.8f)
                .height(60.dp)
                .align(Alignment.CenterStart)
        )
        IconButton(
            onClick = {
                onSend(text)
                text = ""
            },
            modifier = Modifier.align(Alignment.CenterEnd)
        ){
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "Send",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }
}
