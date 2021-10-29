package com.example.professorapp.view.curso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.professorapp.R
import com.example.professorapp.databinding.FragmentCadCourseBinding
import com.example.professorapp.databinding.FragmentListagemBinding


class CadCourseFragment : Fragment() {

    private lateinit var viewBinding: FragmentCadCourseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCadCourseBinding.inflate(layoutInflater)
        return viewBinding.root
    }
}