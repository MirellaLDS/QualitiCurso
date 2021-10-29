package com.example.professorapp.view.curso

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.professorapp.repository.model.CourseModel
import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.network.CourseNetwork
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CursoViewModel(private val dispatcher: CoroutineContext, private val network: CourseNetwork) :
    ViewModel() {

    private val _getState = MutableStateFlow<UIState<List<CourseModel>>>(UIState.Loading)
    val uiState get() = _getState.asStateFlow()

    private val _getStateInsert = MutableStateFlow<UIState<List<CourseModel>>>(UIState.Loading)
    val uiStateInsert get() = _getStateInsert.asStateFlow()

    fun getAllCursos() {
        viewModelScope.launch(dispatcher) {
            network.getAllCourses().collect { state ->
                when(state) {
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

    fun insert(value: String) {
        viewModelScope.launch(dispatcher) {
            network.insertCurso(value).collect { state ->
                when(state) {
                    is State.Success -> {
                        _getStateInsert.emit(
                            UIState.Success(state.data)
                        )
                    }
                    is State.Error -> {
                        _getStateInsert.emit(
                            UIState.Error(" dasds")
                        )
                    }
                }
            }
        }
    }
}