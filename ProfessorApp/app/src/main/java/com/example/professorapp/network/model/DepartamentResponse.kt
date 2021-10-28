package com.example.professorapp.network.model

data class DepartamentResponse(
    val id: String,
    val name: String,
    val professors: ProfessorResponse
)