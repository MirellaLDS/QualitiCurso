package com.example.professorapp.view.departament

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.professorapp.network.DepartamentNetwork
import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Departament
import com.example.professorapp.repository.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DepartamentViewModel(
    private val dispatcher: CoroutineContext,
    private val network: DepartamentNetwork
) : ViewModel() {

    private val _getState = MutableStateFlow<UIState<List<Departament>>>(UIState.Loading)
    val uiState get() = _getState.asStateFlow()

    fun getAllDepartaments() {
        viewModelScope.launch(dispatcher) {
            network.getAllDepartaments().collect { state ->
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