package com.beva.momoapplication.source

import androidx.lifecycle.LiveData
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX


class DefaultRepository(private val remoteDataSource: ZooDataSource,
                        private val localDataSource: ZooDataSource) : ZooRepository {
    override suspend fun getProperties(): Result<List<ResultX>> {
        return remoteDataSource.getProperties()
    }

    override suspend fun getAnimalsResult(): Result<List<ResultXXX>> {
        return remoteDataSource.getAnimalsResult()
    }

}
