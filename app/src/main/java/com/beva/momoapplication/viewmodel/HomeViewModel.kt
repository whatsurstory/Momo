package com.beva.momoapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beva.momoapplication.LoadApiStatus
import com.beva.momoapplication.R
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.network.ZooApi
import com.beva.momoapplication.source.Result
import com.beva.momoapplication.source.Util.getString
import com.beva.momoapplication.source.ZooRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber


class HomeViewModel(private val zooRepository: ZooRepository) : ViewModel() {

    private val _properties = MutableLiveData<List<ResultX>>()

    val properties: LiveData<List<ResultX>>
        get() = _properties

    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    // error: The internal MutableLiveData that stores the error of the most recent request
    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    private val _navigateToSelectedProperty = MutableLiveData<ResultX?>()

    val navigateToSelectedProperty: MutableLiveData<ResultX?>
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
        getZooProperties(true)
        Timber.i("init getZooProperties")
    }

    private fun getZooProperties(isInitial: Boolean = false) {

        coroutineScope.launch {
            if (isInitial) _status.value = LoadApiStatus.LOADING
            val listResult = zooRepository.getProperties()
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
                    _error.value = getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false
        }
        Timber.d("_properties: $_properties")
    }

    fun refresh() {
        if (status.value != LoadApiStatus.LOADING) {
            getZooProperties()
        }
    }

    fun displayPropertyDetails(zooProperty: ResultX) {
        _navigateToSelectedProperty.value = zooProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}