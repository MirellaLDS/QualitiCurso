package com.example.professorapp.network

import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Departament
import com.example.professorapp.service.DepartamentService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DepartamentNetwork(private val service: DepartamentService) {
    suspend fun getAllDepartaments(): Flow<State<Departament>> {
        val mutableSharedFlow = MutableSharedFlow<State<Departament>>(replay = 1)
        val resp = service.getAllDepartament().execute()
        if (resp.isSuccessful) {
            mutableSharedFlow.emit(
                State.Success(
                    resp.body()?.map {
                        Departament(
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