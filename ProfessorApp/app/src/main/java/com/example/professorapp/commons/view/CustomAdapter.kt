package com.example.professorapp.commons.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.professorapp.R
import com.example.professorapp.databinding.ItemViewBinding
import com.example.professorapp.commons.model.ListData

class CustomAdapter(private val dataSet: MutableList<ListData> = mutableListOf()) :
    RecyclerView.Adapter<CustomAdapter.WordsViewHolder>() {

    inner class WordsViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(value: ListData) {
            val textView: TextView = view.findViewById(R.id.textView)
            textView.text = value.name

            val container: ConstraintLayout = view.findViewById(R.id.container)
            container.setOnClickListener {
                value.onItemClick?.invoke(value.detailValue)
            }
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

    fun setData(data: List<ListData>) {
        dataSet.clear()
        dataSet.addAll(data)
        notifyDataSetChanged()
    }
}