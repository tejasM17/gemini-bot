package com.example.geminibot.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.geminibot.data.Message
import com.example.geminibot.viewmodel.ChatViewmodel
import com.example.geminibot.viewmodel.message

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val viewmodel: ChatViewmodel = ChatViewmodel()

    Column(
        modifier = modifier,
    ) {
        Text(
            text = "CHAT PAGE",
            style = MaterialTheme.typography.headlineLarge
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
                .padding(bottom = 5.dp)
        ) {
            items(message){txt ->
                MessageBubble(txt)
            }
        }

        MessageInput(onSend = {msg ->
            viewmodel.sendMessage(msg)
        })
    }
}

@Composable
fun MessageBubble(msg: Message) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 4.dp),
        horizontalArrangement = if (msg.isUser) Arrangement.End else Arrangement.Start
    ) {
        Text(
            msg.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(4.dp),
            color = if (msg.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun MessageInput(onSend: (String) -> Unit) {
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(bottom = 30.dp)
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
                        onSend(message.trim())
                        message=""
                    }
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        )
    }
}
