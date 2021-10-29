package com.example.professorapp.network.model

data class CourseResponse(
    var id: String,
    var name: String,
    @Transient var allocations: Any
)

data class CoursePost(
    var name: String
)
