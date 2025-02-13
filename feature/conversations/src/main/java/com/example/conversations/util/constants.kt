package com.example.conversations.util

import androidx.annotation.StringRes
import com.example.conversations.R

data class Tab(
    @StringRes val title: Int
)
val TABS = listOf(
    Tab(R.string.chat),
    Tab(R.string.call)
)