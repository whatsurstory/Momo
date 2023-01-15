package com.beva.momoapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.network.ZooApi
import kotlinx.coroutines.launch
import timber.log.Timber



class HomeViewModel: ViewModel() {

    // Internally, we use a MutableLiveData, because we will be updating the List of MarsProperty
    // with new values
    private val _properties = MutableLiveData<List<ResultX>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val properties: LiveData<List<ResultX>>
        get() = _properties

    private val result = mutableListOf<ResultX>()

    // Internally, we use a MutableLiveData to handle navigation to the selected property
    private val _navigateToSelectedProperty = MutableLiveData<ResultX>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedProperty: LiveData<ResultX>
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