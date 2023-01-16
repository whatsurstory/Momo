package com.beva.momoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.beva.momoapplication.model.AnimalData
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX

class AnimalDetailViewModel(animalData: ResultXXX): ViewModel() {

    private val _selectedProperty = MutableLiveData<ResultXXX>()

    val selectedProperty: LiveData<ResultXXX>
        get() = _selectedProperty

    private val _imageItem = MutableLiveData<List<String>>()
    val imageItem: LiveData<List<String>>
        get() = _imageItem


    init {
        _selectedProperty.value = animalData
        getImage()
    }

    private fun getImage(){
        val detailImage = mutableListOf<String>()
        if (selectedProperty.value?.a_pic01_url?.isNotEmpty() == true) {
            selectedProperty.value?.a_pic01_url?.let {
                detailImage.add(it)
            }
        }
        if (selectedProperty.value?.a_pic02_url?.isNotEmpty() == true) {
            selectedProperty.value?.a_pic02_url?.let {
                detailImage.add(it)
            }
        }
        if (selectedProperty.value?.a_pic03_url?.isNotEmpty() == true) {
            selectedProperty.value?.a_pic03_url?.let {
                detailImage.add(it)
            }
        }
        if (selectedProperty.value?.a_pic04_url?.isNotEmpty() == true) {
            selectedProperty.value?.a_pic04_url?.let {
                detailImage.add(it)
            }
        }

        _imageItem.value = detailImage
    }

}
