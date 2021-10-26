package com.example.professorapp.service

import com.example.professorapp.network.model.DepartamentResponse
import retrofit2.Call
import retrofit2.http.GET

interface DepartamentService {
    @GET("departments")
    fun getAllDepartament(): Call<List<DepartamentResponse>>
}