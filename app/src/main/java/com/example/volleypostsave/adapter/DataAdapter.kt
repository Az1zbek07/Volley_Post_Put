package com.example.volleypostsave.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.volleypostsave.databinding.ItemLayoutBinding
import com.example.volleypostsave.model.Data

class DataAdapter: ListAdapter<Data, DataAdapter.VHolder>(DiffCallback()) {
    lateinit var onClick: (id: Int) -> Unit
    private class DiffCallback: DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VHolder {
        return VHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: Data){
            binding.textName.text = data.first_name
            itemView.setOnClickListener {
                onClick(data.id)
            }
        }
    }
}