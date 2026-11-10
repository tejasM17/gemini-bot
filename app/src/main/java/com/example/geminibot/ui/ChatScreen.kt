package com.example.geminibot.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
fun ChatScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "CHAT PAGE",
            style = MaterialTheme.typography.headlineLarge
        )
        MessageInput()
    }
}


@Composable
fun MessageInput() {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = {message = it},
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(35.dp),
            placeholder = {Text("Ask Anything")},
            trailingIcon = {
                IconButton(
                    onClick = {
                        Log.d("MessageSent",message)
                        message=""
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        )

    }
}
