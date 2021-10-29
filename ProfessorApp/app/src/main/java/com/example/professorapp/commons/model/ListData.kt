package com.example.professorapp.commons.model

data class ListData(
    val name: String,
    val detailValue: String = "",
    var onItemClick: ((nome: String) -> Unit)? = null
)