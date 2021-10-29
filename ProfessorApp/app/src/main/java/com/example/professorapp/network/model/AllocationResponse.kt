package com.example.professorapp.network.model

data class AllocationResponse(
    val id: Int,
    val startHour: String,
    val endHour: String,
    val dayOfWeek: String,
    val professor: ProfessorResponse,
    val course: CourseResponse
)
