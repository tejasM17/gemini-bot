package com.example.geminibot.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.launch

val model = Firebase.ai(backend= GenerativeBackend.googleAI())
    .generativeModel("gemini-3.5-flash")

class ChatViewmodel: ViewModel() {
    fun sendMessage(question: String) {
        viewModelScope.launch {
            Log.d("GeminiResponse", "Qstn : $question")
            try {
                val response = model.generateContent(question)
                val answer = response.text?: "No response.."

                Log.d("GeminiResponse", "Ans $answer")
            } catch (e: Exception){
                Log.e("GeminiResponse", "Error: ${e.message}")
            }
        }
    }
}
