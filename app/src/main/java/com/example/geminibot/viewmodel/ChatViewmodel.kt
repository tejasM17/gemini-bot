package com.example.geminibot.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geminibot.data.Message
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.launch

val model = Firebase.ai(backend= GenerativeBackend.googleAI())
    .generativeModel("gemini-3.5-flash")

val message = mutableStateListOf<Message>()

class ChatViewmodel: ViewModel() {
    fun sendMessage(question: String) {
        if (question.isBlank()) return

        message.add(Message(text = question, isUser = true))
        val chatHistory = buildChatHistory(message)

        viewModelScope.launch {
            Log.d("GeminiResponse", "Qtn : $question")
            try {
                val response = model.generateContent(question + chatHistory)
                val answer = response.text ?: "No response.."
                message.add(Message(text = answer, isUser = false))

                Log.d("GeminiResponse", "Ans: $answer")
            } catch (e: Exception){
                Log.d("GeminiResponse", "Error : ${e.message}")
            }
        }
    }

    private fun buildChatHistory(message: List<Message>): String {
        val history = StringBuilder()

        for (msg in message){
            if (msg.isUser){
                history.append("User : ${msg.text}")
            }
            else {
                history.append("Gemini-bot : ${msg.text}")
            }
        }

        return history.toString()
    }
}
