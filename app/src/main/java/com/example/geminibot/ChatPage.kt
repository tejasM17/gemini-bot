package com.example.geminibot

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Chat page",
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge
    )
}