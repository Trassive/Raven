package com.example.chat.ui.util.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.chat.ui.model.ChatMessage
import com.example.chat.ui.model.MessageContent
import com.example.framework.ui.Avatar

@Composable
fun MessageItem(
    chatMessage: ChatMessage
){
    Row(
        horizontalArrangement = if (chatMessage.isMine)
            Arrangement.End else Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        if(!chatMessage.isMine){
            Avatar(
                imageUrl = chatMessage.avatar,
                contentDes = chatMessage.senderName,
                size = 42.dp,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column{
            if(!chatMessage.isMine){
                Text(
                    text = chatMessage.senderName,
                    fontWeight = FontWeight.Bold
                )
            }else{
                Spacer(Modifier.height(8.dp))
            }

            when(val content = chatMessage.message){
                is MessageContent.Text -> {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if(chatMessage.isMine) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    ){
                        Text(
                            text = content.text,
                            color = if(chatMessage.isMine) MaterialTheme.colorScheme.onPrimary
                            else Color.White,
                            modifier = Modifier.padding(8.dp),
                        )
                    }

                }
                is MessageContent.Image -> {
                    AsyncImage(
                        model = content.url,
                        contentDescription = "Image",
                        modifier = Modifier
                            .size(40.dp),
                        contentScale = ContentScale.Crop
                    )
                }
                is MessageContent.Video -> {
                    // Video(url = content.url)
                }
                is MessageContent.Audio -> {
                    // Audio(url = content.url)
                }
            }
            Text(
                text = chatMessage.timeStamp,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun MessageItemPreviewMine() {
    MessageItem(
        chatMessage = ChatMessage(
            id = "1",
            senderName = "Me",
            avatar = "https://example.com/avatar.jpg",
            message = MessageContent.Text("Hello, this is my message."),
            timeStamp = "12:00",
            isMine = true
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MessageItemPreviewNotMine() {
    MessageItem(
        chatMessage = ChatMessage(
            id = "2",
            senderName = "John Doe",
            avatar = "https://example.com/avatar.jpg",
            message = MessageContent.Text("Hello, this is a message from John."),
            timeStamp = "12:01",
            isMine = false
        )
    )
}