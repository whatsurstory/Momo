package com.beva.momoapplication

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.beva.momoapplication.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    val imgUri = url?.toUri()?.buildUpon()?.scheme("https")?.build()
    Glide.with(context)
        .load(imgUri)
        .placeholder(R.drawable.place_holder)
        .into(this)
}


enum class CurrentFragmentType(val value: String) {
    HOME(ZooApplication.instance.resources.getString(R.string.zoo)),
    HOUSEDETAIL("HOUSE_DETAIL"),
    ANIMALDETAIL("ANIMAL_DETAIL")
}

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as ZooApplication).zooRepository
    return ViewModelFactory(repository)
}