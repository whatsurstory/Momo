package com.beva.momoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beva.momoapplication.LoadApiStatus
import com.beva.momoapplication.R
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX
import com.beva.momoapplication.network.ZooApi
import com.beva.momoapplication.source.Result
import com.beva.momoapplication.source.Util
import com.beva.momoapplication.source.ZooRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class HouseDetailVieModel(private val zooRepository: ZooRepository, zooProperty: ResultX) : ViewModel() {

    private val _selectedProperty = MutableLiveData<ResultX>()

    val selectedProperty: LiveData<ResultX>
        get() = _selectedProperty

    private val _properties = MutableLiveData<List<ResultXXX>>()

    val properties: LiveData<List<ResultXXX>>
        get() = _properties

    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _navigateToSelectedProperty = MutableLiveData<ResultXXX?>()

    val navigateToSelectedProperty: MutableLiveData<ResultXXX?>
        get() = _navigateToSelectedProperty

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    init {
        _selectedProperty.value = zooProperty
        getAnimalsProperties(true)
    }

    private fun getAnimalsProperties(isInitial: Boolean = false) {
        coroutineScope.launch {
//            val listResult = ZooApi.retrofitService.getAnimalsResult()
//            _properties.value = listResult.result.results
            if (isInitial) _status.value = LoadApiStatus.LOADING
            val listResult = zooRepository.getAnimalsResult()
            _properties.value = when (listResult) {
                is Result.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    listResult.data
                }
                is Result.Fail -> {
                    _error.value = listResult.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = listResult.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = Util.getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
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