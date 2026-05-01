package com.example.geminibot

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp

@Composable
fun ChatPage(modifier: Modifier = Modifier) {

    val viewmodel: ChatViewmodel = ChatViewmodel()
    val message = viewmodel.messages

    Column(
        modifier = modifier,
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
        ) {
            items(message) {msg ->
                MessageBubble(msg)
            }
        }
        MessageInput(
            onSend = {msg ->
                viewmodel.sendMessage(msg)
            }
        )
    }
}

@Composable
fun MessageBubble(msg: Message) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp)
            .fillMaxSize()
    ) {
        Text(
            text = msg.text,
            fontSize = 20.sp
        )
    }
}


@Composable
fun MessageInput(onSend: (String) -> Unit) {

    var message by remember { mutableStateOf("") }

    Row {
        OutlinedTextField(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            value = message,
            onValueChange = {message = it},
            shape = RoundedCornerShape(15.dp),
            placeholder = {
                Text(text = "Ans anything...")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onSend(message)
                        message = ""
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        )
    }
}
