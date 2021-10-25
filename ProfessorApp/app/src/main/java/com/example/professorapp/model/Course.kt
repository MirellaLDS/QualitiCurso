package com.example.professorapp.model

open class Course(
    open var id: String,
    open var name: String
)

data class CourseResponse(
    var id: String,
    var name: String,
    @Transient var listAllocations: Any
)
