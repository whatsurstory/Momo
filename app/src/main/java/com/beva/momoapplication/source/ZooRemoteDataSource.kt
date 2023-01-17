package com.beva.momoapplication.source

import com.beva.momoapplication.R
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX
import com.beva.momoapplication.network.ZooApi
import com.beva.momoapplication.source.Util.getString
import com.beva.momoapplication.source.Util.isInternetConnected
import timber.log.Timber


object ZooRemoteDataSource: ZooDataSource {
    override suspend fun getProperties(): Result<List<ResultX>> {

        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = ZooApi.retrofitService.getProperties()

            listResult.error?.let {
                return Result.Fail(it)
            }
            Result.Success(listResult.result.results)
        } catch (e: Exception) {
            Timber.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }

    override suspend fun getAnimalsResult(): Result<List<ResultXXX>> {
        if (!isInternetConnected()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }

        return try {
            // this will run on a thread managed by Retrofit
            val listResult = ZooApi.retrofitService.getAnimalsResult()

            listResult.error?.let {
                return Result.Fail(it)
            }
            Result.Success(listResult.result.results)
        } catch (e: Exception) {
            Timber.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }

}