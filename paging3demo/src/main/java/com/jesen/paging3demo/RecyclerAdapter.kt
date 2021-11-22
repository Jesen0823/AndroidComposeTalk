package com.jesen.paging3demo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter :
    PagingDataAdapter<Question, RecyclerAdapter.MyViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("Adapter--","getItem: ${getItem(position)}\n position:$position")

        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.MyViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvQuestion: TextView = view.findViewById(R.id.tvQue)
        private val tvAnsow: TextView = view.findViewById(R.id.tvAns)
        private val tvExplain: TextView = view.findViewById(R.id.tvExp)

        fun bind(data: Question) {
            Log.d("Adapter--","data: ${data.toString()}")
            tvQuestion.text = data.question
            tvAnsow.text = data.answer
            tvExplain.text = data.explain
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem.question == newItem.question
        }

    }
}