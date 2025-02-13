package com.example.conversations

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.conversations.util.components.CustomTopAppBar
import com.example.conversations.util.TABS
import com.example.conversations.util.components.ConversationList


@Composable
fun ConversationListScreen(
    onNewChatClick: () -> Unit,
    onChatClick: (Int) -> Unit
){
    val pagerState = rememberPagerState(initialPage = 0){1}
    val index by remember{ mutableIntStateOf(1) }
    Scaffold(
        topBar = {
            CustomTopAppBar(onChatClick = onChatClick, tabs = TABS)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNewChatClick() },
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }

    ){innerPadding ->
        Surface(modifier =  Modifier.padding(innerPadding)){
            when (index) {
                1 -> ConversationList(
                    list = emptyList(),
                    onChatClick = onChatClick,
                )
            }
        }

    }
}



@Preview
@Composable
fun ConversationScreen(){
    ConversationListScreen(
        onNewChatClick = {},
        onChatClick = {}
    )
}
