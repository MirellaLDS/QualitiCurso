package com.example.professorapp.view.departament

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.professorapp.R
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.view.curso.CustomAdapter
import com.example.professorapp.view.model.ListData
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class DepartamentFragment : Fragment() {

    private val viewModelDepartment: DepartamentViewModel by inject()
    private lateinit var viewBinding: FragmentListagemBinding
    private lateinit var customAdapter: CustomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentListagemBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customAdapter = CustomAdapter()
        viewBinding.rvListWords.adapter = customAdapter

        viewModelDepartment.getAllDepartaments()
        setupRequestGetAll()
    }

    private fun setupRequestGetAll() {
        lifecycleScope.launchWhenCreated {
            viewModelDepartment.uiState.collect { state ->
                when (state) {
                    is UIState.Success -> {
                        viewBinding.pbLoading.visibility = View.GONE

                        val data = state.data.map { ListData(it.name) }
                        customAdapter.setData(data)
                    }
                    is UIState.Error -> {
                        viewBinding.pbLoading.visibility = View.GONE
                        AlertDialog.Builder(requireContext())
                            .setTitle("Algo errado!")
                            .setMessage(state.exception)
                            .setNegativeButton("OK") { _, _ ->

                            }
                            .show()
                    }
                    is UIState.Loading -> {
                        viewBinding.pbLoading.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}