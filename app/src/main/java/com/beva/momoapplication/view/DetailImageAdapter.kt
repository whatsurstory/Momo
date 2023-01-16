package com.beva.momoapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beva.momoapplication.databinding.ItemPictureBinding
import com.beva.momoapplication.loadImage

class DetailImageAdapter :
    ListAdapter<String, DetailImageAdapter.DetailImageViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailImageViewHolder {
        return DetailImageViewHolder(
            ItemPictureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DetailImageViewHolder(private var binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.animalDetailImage.loadImage(item)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}