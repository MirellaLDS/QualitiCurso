package com.example.professorapp.repository.model

sealed class UIState<out T> {
    object Loading: UIState<Nothing>()
    data class Success<T>(val data: T): UIState<T>()
    data class Error(val exception: String): UIState<Nothing>()
}