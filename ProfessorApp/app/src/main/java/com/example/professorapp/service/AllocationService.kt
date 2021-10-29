package com.example.professorapp.service

import com.example.professorapp.network.model.AllocationResponse
import retrofit2.Call
import retrofit2.http.GET

interface AllocationService {

    @GET("allocations")
    fun getAll(): Call<List<AllocationResponse>>
}