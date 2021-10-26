package com.example.professorapp.network.model

data class CourseResponse(
    var id: String,
    var name: String,
    @Transient var listAllocations: Any
)
