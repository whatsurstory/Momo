package com.beva.momoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.network.ZooApi
import kotlinx.coroutines.launch
import timber.log.Timber


class HomeViewModel : ViewModel() {

    private val _properties = MutableLiveData<List<ResultX>>()

    val properties: LiveData<List<ResultX>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<ResultX?>()

    val navigateToSelectedProperty: MutableLiveData<ResultX?>
        get() = _navigateToSelectedProperty

    init {
        getZooProperties()
        Timber.i("init getZooProperties")
    }

    private fun getZooProperties() {
        viewModelScope.launch {
            val listResult = ZooApi.retrofitService.getProperties()
            _properties.value = listResult.result.results
        }
        Timber.d("_properties: $_properties")
    }

    fun displayPropertyDetails(zooProperty: ResultX) {
        _navigateToSelectedProperty.value = zooProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}