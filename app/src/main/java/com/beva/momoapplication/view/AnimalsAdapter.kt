package com.beva.momoapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBindings
import com.beva.momoapplication.databinding.ItemHomeBinding
import com.beva.momoapplication.loadImage
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX

class AnimalsAdapter: ListAdapter<ResultXXX, AnimalsAdapter.ViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<ResultXXX>() {
        override fun areItemsTheSame(oldItem: ResultXXX, newItem: ResultXXX): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultXXX, newItem: ResultXXX): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultXXX) {
            binding.houseName.text = item.a_name_ch
            binding.houseInfo.text = item.a_alsoknown
            binding.itemHomeImage.loadImage(item.a_pic01_url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}