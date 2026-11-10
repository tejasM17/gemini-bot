package com.example.geminibot.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
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

        viewModelScope.launch {
            Log.d("GeminiResponse", "Qstn : $question")
            try {
                val response = model.generateContent(question)
                val answer = response.text ?: "No response.."
                message.add(Message(text = answer, isUser = false))

                Log.d("GeminiResponse", "Ans $answer")
            } catch (e: Exception) {
                Log.e("GeminiResponse", "Error: ${e.message}")
            }
        }
    }
}
