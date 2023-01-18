package com.beva.momoapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.source.ZooRepository
import com.beva.momoapplication.viewmodel.HouseDetailVieModel

@Suppress("UNCHECKED_CAST")
class HouseDetailVieModelFactory(
    private val zooRepository: ZooRepository,
    private val zooProperty: ResultX
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(HouseDetailVieModel::class.java) ->
                    HouseDetailVieModel(zooRepository, zooProperty)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}
