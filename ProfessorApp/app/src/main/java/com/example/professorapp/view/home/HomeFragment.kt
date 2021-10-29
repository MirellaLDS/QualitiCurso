package com.example.professorapp.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.professorapp.R
import com.example.professorapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentHomeBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.lvLinearLayoutCourse.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listagemFragment2)
        }

        viewBinding.lvLinearLayoutDepartament.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listagemFragmentDepartment)
        }

        viewBinding.lvLinearLayoutProfessor.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listagemFragmentProfessor)
        }

        viewBinding.lvLinearLayoutAllocation.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_listagemFragmentAllocation)
        }
    }

}