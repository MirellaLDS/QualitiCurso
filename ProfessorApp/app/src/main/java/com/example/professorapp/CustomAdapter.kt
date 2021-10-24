package com.example.professorapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.professorapp.databinding.ItemViewBinding

class CustomAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<CustomAdapter.WordsViewHolder>() {

    inner class WordsViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(value: String) {
            val textView: TextView = view.findViewById(R.id.textView)
            textView.text = value

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val viewBinding =
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordsViewHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}