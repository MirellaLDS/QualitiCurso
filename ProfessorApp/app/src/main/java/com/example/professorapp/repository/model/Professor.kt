package com.example.professorapp.repository.model

import com.example.professorapp.network.model.DepartamentResponse

data class Professor(
    val cpf: String,
    val department: DepartamentResponse,
    val id: Int,
    val name: String
)
