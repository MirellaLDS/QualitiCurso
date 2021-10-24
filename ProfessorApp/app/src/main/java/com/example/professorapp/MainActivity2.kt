package com.example.professorapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.professorapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.rvListWords.adapter = CustomAdapter(listOf("Mirella", "Amanda", "Gabriel", "Luzia"))

    }
}