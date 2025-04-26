package com.example.conversations.util.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.conversations.R
import com.example.conversations.util.Tab

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CustomTopAppBar(
    onChatClick: (String) -> Unit,
    tabs: List<Tab>
) {
    Column{
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            }
        )
        TabRow(
            selectedTabIndex = 0,
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = index == 0,
                    onClick = { }
                ) {
                    Text(
                        text = stringResource(tab.title),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
@Preview
@Composable
fun CustomTopAppBarPreview(){
    CustomTopAppBar(
        onChatClick = {},
        tabs = listOf(
            Tab(R.string.chat),
            Tab(R.string.call)
        )
    )
}