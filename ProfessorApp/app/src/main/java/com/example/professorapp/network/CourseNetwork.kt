package com.example.professorapp.network

import com.example.professorapp.repository.model.CourseModel
import com.example.professorapp.network.model.State
import com.example.professorapp.service.CourseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class CourseNetwork(private val service: CourseService) {

    suspend fun getAllCourses(): Flow<State<CourseModel>> {
        val mutableSharedFlow = MutableSharedFlow<State<CourseModel>>(replay = 1)
        val response = service.getAll().execute()

        if (response.isSuccessful) {
            mutableSharedFlow.emit(
                State.Success(
                    response.body()?.map {
                        CourseModel(
                            it.id,
                            it.name
                        )
                    } ?: emptyList()
                )
            )
        } else {
            mutableSharedFlow.emit(
                State.Error(" Erro ")
            )
        }

        return mutableSharedFlow
    }

}