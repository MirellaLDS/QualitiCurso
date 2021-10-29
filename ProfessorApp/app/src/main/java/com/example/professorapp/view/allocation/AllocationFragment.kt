package com.example.professorapp.view.allocation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.professorapp.R
import com.example.professorapp.commons.model.ListData
import com.example.professorapp.commons.view.CustomAdapter
import com.example.professorapp.databinding.FragmentAllocationBinding
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.view.curso.CursoViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class AllocationFragment : Fragment() {

    private val viewModel: AllocationViewModel by inject()
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

        setupObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UIState.Success -> {
                        viewBinding.pbLoading.visibility = View.GONE
                        val data = state.data.map {
                            ListData(
                                " ${it.dayOfWeek} - ${it.startHour} - ${it.endHour} ",
                                " ${it.professor?.name} + ${it.course?.name} ",
                                onItemClick = ::action
                            )
                        }
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

    fun action(value: String) {
        Toast.makeText(requireContext(), value, Toast.LENGTH_LONG).show()
    }

}