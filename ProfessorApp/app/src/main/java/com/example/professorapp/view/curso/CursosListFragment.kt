package com.example.professorapp.view.curso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.professorapp.config.RetrofitConfig
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.view.departament.DepartamentViewModel
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject
import retrofit2.Retrofit

class CursosListFragment : Fragment() {

    private val viewModel: CursoViewModel by inject()
    private val viewModelDepartament: DepartamentViewModel by inject()
    private lateinit var viewBinding: FragmentListagemBinding
    private lateinit var retrofitConfig: Retrofit
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
        retrofitConfig = RetrofitConfig.retrofitInstance
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
        viewModelDepartament.getAllDepartaments()
        viewModel.getAllCursos()
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when(state) {
                    is UIState.Success -> {
                        viewBinding.pbLoading.visibility = View.GONE
                        customAdapter.setData(state.data)
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