package com.example.professorapp.repository.model

import com.example.professorapp.network.model.CourseResponse
import com.example.professorapp.network.model.ProfessorResponse

data class Allocation(
    val id: Int,
    val startHour: String,
    val endHour: String,
    val dayOfWeek: String,
    val professor: ProfessorResponse? = null,
    val course: CourseResponse? = null
)
