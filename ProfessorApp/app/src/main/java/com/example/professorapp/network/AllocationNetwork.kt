package com.example.professorapp.network

import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Allocation
import com.example.professorapp.repository.model.CourseModel
import com.example.professorapp.service.AllocationService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class AllocationNetwork(private val service: AllocationService) {

    suspend fun getAll(): Flow<State<Allocation>> {
        val mutableSharedFlow = MutableSharedFlow<State<Allocation>>(replay = 1)
        val response = service.getAll().execute()

        if (response.isSuccessful) {
            mutableSharedFlow.emit(
                State.Success(
                    response.body()?.map {
                        Allocation(
                            it.id,
                            it.startHour,
                            it.endHour,
                            it.dayOfWeek,
                            it.professor,
                            it.course
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