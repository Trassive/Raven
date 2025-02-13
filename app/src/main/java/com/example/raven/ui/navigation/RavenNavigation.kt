package com.example.raven.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.example.chat.ChatScreen
import com.example.conversations.ConversationListScreen
import com.example.create_chat.NewChatScreen
import com.example.framework.navigation.Routes
import com.example.framework.navigation.uri

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.ConversationList
    ){
        addConversationList(navController)
        addChat(navController)
        addNewChat(navController)
    }
}

fun NavGraphBuilder.addNewChat(navController: NavHostController) {
    composable<Routes.NewChat> {
        NewChatScreen(
            onChatCreated = { chatId ->
                navController.navigate(Routes.Chat(chatId))
            },
            onBack = {
                navController.popBackStack()
            }
        )
    }
}

fun NavGraphBuilder.addChat(navController: NavHostController) {
    composable<Routes.Chat>(
        deepLinks = listOf(
            navDeepLink<Routes.Chat>("$uri/chat")
        )
    ){ backStackEntry ->
        val chatId = backStackEntry.toRoute<Routes.Chat>().chatId
        ChatScreen(
            chatId = chatId,
            onBack = {
                navController.popBackStack()
            }
        )
    }
}

fun NavGraphBuilder.addConversationList(navController: NavHostController) {
    composable<Routes.ConversationList> {
        ConversationListScreen(
            onNewChatClick = {
                navController.navigate(Routes.NewChat)
            },
            onChatClick = { chatId ->
                navController.navigate(Routes.Chat(chatId))
            }
        )
    }
}
