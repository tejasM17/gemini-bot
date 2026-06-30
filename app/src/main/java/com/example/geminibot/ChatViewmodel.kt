package com.example.geminibot

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.GenerativeBackend
import kotlinx.coroutines.launch

class ChatViewmodel: ViewModel() {
    val model = Firebase.ai(backend = GenerativeBackend.googleAI())
        .generativeModel("gemini-3.5-flash")
    fun sendMessage(question: String) {
        viewModelScope.launch {
            val response = model.generateContent(prompt = question)
            val answer = response.text ?: "No response, try again"
            Log.d("GEMINIAI", answer)
        }
    }
}
