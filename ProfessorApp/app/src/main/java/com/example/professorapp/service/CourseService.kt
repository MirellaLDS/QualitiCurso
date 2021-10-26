package com.example.professorapp.service

import com.example.professorapp.network.model.CourseResponse
import retrofit2.Call
import retrofit2.http.GET

interface CourseService {

    @GET("courses")
    fun getAll(): Call<List<CourseResponse>>
}