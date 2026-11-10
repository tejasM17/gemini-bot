package com.example.geminibot.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    Text(
        text = "CHAT PAGE",
        modifier = modifier,
        style = MaterialTheme.typography.headlineLarge
    )
}
