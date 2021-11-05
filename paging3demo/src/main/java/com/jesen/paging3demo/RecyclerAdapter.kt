package com.jesen.paging3demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter : PagingDataAdapter<CharacterData, RecyclerAdapter.MyViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder{
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent)

        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val imageVv :ImageView = view.findViewById(R.id.imageVw)
        val tvName:TextView = view.findViewById(R.id.tvName)
        val tvDes:TextView = view.findViewById(R.id.tvDesc)

        fun  bind(data: CharacterData){
            tvName.text = data.name
            tvDes.text = data.species
            Glide.with(imageVv)
                .load(data.image)
                .into(imageVv)
        }
    }

    class DiffUtilCallback:DiffUtil.ItemCallback<CharacterData>(){
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species
        }

    }

}