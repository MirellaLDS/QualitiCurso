package com.example.professorapp.service

import com.example.professorapp.network.model.CoursePost
import com.example.professorapp.network.model.CourseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface CourseService {

    @GET("courses")
    fun getAll(): Call<List<CourseResponse>>

    @POST("courses")
    fun insert(@Body name: CoursePost): Call<CourseResponse>
}