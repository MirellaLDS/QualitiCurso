package com.example.professorapp.network.model

sealed class State<out T> {
    data class Success<T>(val data: List<T>): State<T>()
    data class Error(val exception: String): State<Nothing>()
}