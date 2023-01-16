package com.beva.momoapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beva.momoapplication.R
import com.beva.momoapplication.ZooApplication
import com.beva.momoapplication.databinding.ItemHomeBinding
import com.beva.momoapplication.loadImage
import com.beva.momoapplication.model.ResultXXX

class AnimalsAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ResultXXX, AnimalsAdapter.ViewHolder>(DiffCallback) {

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
            binding.houseInfo.text =
                item.a_alsoknown.ifEmpty {
                    ZooApplication.instance.resources.getString(
                        R.string.no_aka_information
                    )
                }
            binding.itemHomeImage.loadImage(item.a_pic01_url)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class OnClickListener(val clickListener: (animalProperty: ResultXXX) -> Unit) {
        fun onClick(animalProperty: ResultXXX) = clickListener(animalProperty)
    }

}