package com.beva.momoapplication.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beva.momoapplication.databinding.ItemHomeBinding
import com.beva.momoapplication.model.ResultX
import com.bumptech.glide.Glide
import timber.log.Timber

class HomeAdapter: ListAdapter<ResultX, HomeAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: ItemHomeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultX) {
            binding.houseName.text = item.name
            binding.houseInfo.text = item.info
            binding.houseMemo.visibility = View.VISIBLE
            if (item.memo?.isNotEmpty() == true) {
                binding.houseMemo.text = item.memo
            } else {
                binding.houseMemo.text = "無休館資訊"
            }
//            val newUrl = item.picUrl
//            newUrl.replace("\\","")
//            Timber.d("newUrl $newUrl")
            Glide.with(itemView.context)
                .load(item.picUrl)
                .centerCrop()
                .into(binding.itemHomeImage)
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
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}