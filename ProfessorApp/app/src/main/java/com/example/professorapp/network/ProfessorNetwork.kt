package com.example.professorapp.network

import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Departament
import com.example.professorapp.repository.model.Professor
import com.example.professorapp.service.DepartamentService
import com.example.professorapp.service.ProfessorService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ProfessorNetwork(private val service: ProfessorService) {
    suspend fun getAll(): Flow<State<Professor>> {
        val mutableSharedFlow = MutableSharedFlow<State<Professor>>(replay = 1)
        val resp = service.getAll().execute()
        if (resp.isSuccessful) {
            mutableSharedFlow.emit(
                State.Success(
                    resp.body()?.map {
                        Professor(
                            it.cpf,
                            it.department,
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