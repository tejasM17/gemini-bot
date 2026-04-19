package com.example.geminibot

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun ChatPage(modifier: Modifier = Modifier) {
    Column(modifier = modifier
        .padding(8.dp)
    ) {
        Text(text = "Chat Page")

        MessageInput(onSend = {})
    }
}

@Composable
fun MessageInput(onSend: (String) -> Unit) {

    var message by remember { mutableStateOf("") }

    Row() {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f),
            value = message,
            onValueChange = {message = it},
            placeholder = {Text(text = "Ask anything...")}
        )

        IconButton(onClick = {
            Log.d("message", message)
            onSend(message)
            message = ""
        }) {
            Icon(Icons.Default.Send, contentDescription = "Send msg")
        }
    }
}
