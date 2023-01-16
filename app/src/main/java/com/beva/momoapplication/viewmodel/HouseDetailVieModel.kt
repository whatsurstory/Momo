package com.beva.momoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX
import com.beva.momoapplication.network.ZooApi
import kotlinx.coroutines.launch
import timber.log.Timber

class HouseDetailVieModel(zooProperty: ResultX) : ViewModel() {

    private val _selectedProperty = MutableLiveData<ResultX>()

    val selectedProperty: LiveData<ResultX>
        get() = _selectedProperty

    private val _properties = MutableLiveData<List<ResultXXX>>()

    val properties: LiveData<List<ResultXXX>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<ResultXXX?>()

    val navigateToSelectedProperty: MutableLiveData<ResultXXX?>
        get() = _navigateToSelectedProperty

    init {
        _selectedProperty.value = zooProperty
        getAnimalsProperties()
    }

    private fun getAnimalsProperties() {
        viewModelScope.launch {
            val listResult = ZooApi.retrofitService.getAnimalsResult()
            _properties.value = listResult.result.results
        }
        Timber.d("_properties: $_properties")
    }

    fun displayPropertyDetails(animalsProperty: ResultXXX) {
        _navigateToSelectedProperty.value = animalsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}