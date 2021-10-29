package com.example.professorapp.repository.model

open class Course(
    open var id: String = "",
    open var name: String
)

data class CourseModel(
    var id: String,
    var name: String,
    @Transient var listAllocations: Any? = null
)
