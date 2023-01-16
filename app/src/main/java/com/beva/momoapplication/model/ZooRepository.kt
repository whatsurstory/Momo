package com.beva.momoapplication.model

import kotlin.Result

interface ZooRepository {
    suspend fun getZooProperties(): Result<List<ResultX>>

    suspend fun getAnimalsProperties(): Result<List<ResultXXX>>
}
