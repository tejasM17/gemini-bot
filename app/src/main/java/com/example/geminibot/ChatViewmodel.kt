package com.example.geminibot

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Message(val user: Boolean, val text: String)

class ChatViewmodel: ViewModel() {

    val messages = mutableStateListOf<Message>()

    fun sendMessage(prompt: String) {
        messages.add(Message(user = true, text = prompt))
        Log.d("prompt", prompt)

        messages.add(Message(user = false, text = "fake response"))
    }
}
