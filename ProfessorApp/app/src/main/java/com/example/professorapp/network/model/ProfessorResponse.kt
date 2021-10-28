package com.example.professorapp.network.model

data class ProfessorResponse(
    val cpf: String,
    val department: DepartamentResponse,
    val id: Int,
    val name: String
)
