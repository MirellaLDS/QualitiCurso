package com.example.professorapp.service

import com.example.professorapp.network.model.DepartamentResponse
import com.example.professorapp.network.model.ProfessorResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProfessorService {
    @GET("professors")
    fun getAll(): Call<List<ProfessorResponse>>
}