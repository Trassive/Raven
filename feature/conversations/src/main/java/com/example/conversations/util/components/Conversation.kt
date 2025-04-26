package com.example.conversations.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conversations.model.Conversation
import com.example.framework.ui.Avatar

@Composable
fun Conversation(
    conversation: Conversation,
    onChatComposable: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier
        .fillMaxWidth()
        .height(70.dp)
        .padding(8.dp)){
        Avatar(
            imageUrl = conversation.avatar,
            contentDes = conversation.name,
            size = 50.dp
        )
        Spacer(Modifier.width(8.dp))
        Column(Modifier.weight(0.8f).fillMaxHeight()){
            Text(
                text = conversation.name,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )
            Text(
                text = conversation.messsage,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

        }
        Spacer(Modifier.width(8.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentHeight().fillMaxHeight()
        ){
            Text(text = conversation.timeStamp)
            if(conversation.unreadCount>0){
                Text(
                    text = conversation.unreadCount.toString(),
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(top = 12.dp)
                        .drawBehind {
                            drawCircle(
                                color = Color.Red
                            )
                        }
                        .padding(6.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ConversationPreview(){
    Conversation(
        conversation = Conversation(
            id = "1",
            name = "John Doe",
            messsage = "Hello son",
            timeStamp = "12:00",
            unreadCount = 1,
            avatar = "https://example.com/avatar.jpg"
        ),
        onChatComposable = {}
    )
}