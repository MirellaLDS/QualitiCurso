package com.example.professorapp.view.professor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.professorapp.network.ProfessorNetwork
import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Departament
import com.example.professorapp.repository.model.Professor
import com.example.professorapp.repository.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfessorViewModel(
    private val dispatcher: CoroutineContext,
    private val network: ProfessorNetwork
) : ViewModel() {

    private val _getState = MutableStateFlow<UIState<List<Professor>>>(UIState.Loading)
    val uiState get() = _getState.asStateFlow()

    fun getAllProfessor() {
        viewModelScope.launch(dispatcher) {
            network.getAll().collect { state ->
                when (state) {
                    is State.Success -> {
                        _getState.emit(
                            UIState.Success(state.data)
                        )
                    }
                    is State.Error -> {
                        _getState.emit(
                            UIState.Error(" dasds")
                        )
                    }
                }
            }
        }
    }
}