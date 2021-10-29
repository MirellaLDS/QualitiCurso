package com.example.professorapp.view.curso

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.professorapp.R
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.repository.model.UIState
import com.example.professorapp.commons.model.ListData
import com.example.professorapp.commons.view.CustomAdapter
import kotlinx.coroutines.flow.collect
import org.koin.android.ext.android.inject

class CursosListFragment : Fragment() {

    private val viewModel: CursoViewModel by inject()
    private lateinit var viewBinding: FragmentListagemBinding
    private lateinit var customAdapter: CustomAdapter

    private var data: MutableList<ListData> = mutableListOf()
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentListagemBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customAdapter = CustomAdapter()
        viewBinding.rvListWords.adapter = customAdapter
        setHasOptionsMenu(true)
        setupObserver()

        viewBinding.rvListWords.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager

                if (!isLoading) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size - 1
                    ) {
                        viewModel.getAllCursos()
                        isLoading = true
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCursos()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> findNavController().navigateUp()
            R.id.cad_item -> createDialog()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupObserver() {
        lifecycleScope.launchWhenCreated {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UIState.Success -> {
                        viewBinding.pbLoading.visibility = View.GONE
                        data.addAll(state.data.map { ListData(it.name) })
                        customAdapter.setData(data)
                        isLoading = false
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

        lifecycleScope.launchWhenCreated {
            viewModel.uiStateInsert.collect { state ->
                when (state) {
                    is UIState.Success -> {
                        viewBinding.pbLoading.visibility = View.GONE
                        val value = state.data[0]
                        data.add(
                            ListData(
                                value.name
                            )
                        )
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

    private fun createDialog() {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater

        val view = inflater.inflate(R.layout.dialog_cad, null)
        builder.setView(view)
            .setPositiveButton(
                R.string.bt_cad
            ) { dialog, id ->
                val textView = view.findViewById<TextView>(R.id.courseName)
                viewModel.insert(textView.text.toString())
            }
            .setNegativeButton(
                R.string.bt_cancel
            ) { dialog, id ->
                dialog.dismiss()
            }
        builder.show()
    }

}