package com.beva.momoapplication.view


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beva.momoapplication.R
import com.beva.momoapplication.ZooApplication
import com.beva.momoapplication.databinding.ItemHomeBinding
import com.beva.momoapplication.loadImage
import com.beva.momoapplication.model.ResultX
import com.bumptech.glide.Glide

class HomeAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ResultX, HomeAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultX) {
            binding.houseName.text = item.name
            binding.houseInfo.text = item.info
            binding.houseMemo.visibility = View.VISIBLE
            binding.houseMemo.text =
                if (item.memo?.isNotEmpty() == true) item.memo else ZooApplication.instance.resources.getString(R.string.no_operation_information)
            binding.itemHomeImage.loadImage(item.picUrl)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ResultX>() {
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
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

    class OnClickListener(val clickListener: (zooProperty: ResultX) -> Unit) {
        fun onClick(zooProperty: ResultX) = clickListener(zooProperty)
    }

}