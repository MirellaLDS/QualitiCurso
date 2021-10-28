package com.example.professorapp.view.professor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.commons.model.ListData
import com.example.professorapp.commons.view.CustomAdapter
import org.koin.android.ext.android.inject

class ProfessorListFragment : Fragment() {

    private val viewModel: ProfessorViewModel by inject()
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

        setupRequestGetAll()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (findNavController().popBackStack()) {
            findNavController().navigateUp()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupRequestGetAll() {
        viewModel.getAllProfessor()
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when(state) {
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