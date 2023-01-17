package com.beva.momoapplication.source

import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX
import kotlin.Result

interface ZooDataSource {
    suspend fun getProperties(): com.beva.momoapplication.source.Result<List<ResultX>>
    suspend fun getAnimalsResult(): com.beva.momoapplication.source.Result<List<ResultXXX>>
}