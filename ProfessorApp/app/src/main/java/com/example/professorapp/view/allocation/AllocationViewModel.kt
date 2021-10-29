package com.example.professorapp.view.allocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.professorapp.network.AllocationNetwork
import com.example.professorapp.network.model.State
import com.example.professorapp.repository.model.Allocation
import com.example.professorapp.repository.model.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AllocationViewModel(
    private val dispatcher: CoroutineContext,
    val network: AllocationNetwork
) : ViewModel() {

    private val _getState = MutableStateFlow<UIState<List<Allocation>>>(UIState.Loading)
    val uiState get() = _getState.asStateFlow()

    fun getAll() {
        viewModelScope.launch(dispatcher) {
            network.getAll().collect { state ->
                when(state) {
                    is State.Success -> {
                        _getState.emit(
                            UIState.Success(state.data)
                        )
                    }
                    is State.Error -> {
                        _getState.emit(
                            UIState.Error("Erro na request")
                        )
                    }
                }
            }
        }
    }
}