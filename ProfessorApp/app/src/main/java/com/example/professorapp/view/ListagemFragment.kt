package com.example.professorapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.professorapp.config.RetrofitConfig
import com.example.professorapp.databinding.FragmentListagemBinding
import com.example.professorapp.model.Course
import com.example.professorapp.model.CourseResponse
import com.example.professorapp.service.CourseService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ListagemFragment : Fragment() {

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
        val service = retrofitConfig.create(CourseService::class.java)
        val call = service.getAll()

        call.enqueue(object : Callback<List<CourseResponse>> {
            override fun onResponse(
                call: Call<List<CourseResponse>>,
                response: Response<List<CourseResponse>>
            ) {

                if (response.isSuccessful) {
                    val resposta = response.body()

                    resposta?.let {
                        customAdapter.setData(it)
                    }
                } else {

                    val erro = response.errorBody()?.toString() ?: "Sem resposta"

                    AlertDialog.Builder(requireContext())
                        .setTitle("Algo errado!")
                        .setMessage(erro)
                        .setNegativeButton("OK") { _, _ ->

                        }
                        .show()
                }
            }

            override fun onFailure(call: Call<List<CourseResponse>>, t: Throwable) {
                AlertDialog.Builder(requireContext())
                    .setTitle("ERRO")
                    .setMessage("Algo de errado aconteceu!")
                    .setNegativeButton("OK") { _, _ ->

                    }
                    .show()
            }
        })
    }

}