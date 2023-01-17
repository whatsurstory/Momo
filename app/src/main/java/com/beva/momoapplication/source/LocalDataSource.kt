package com.beva.momoapplication.source

import android.content.Context
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.model.ResultXXX
import com.beva.momoapplication.source.Result

class LocalDataSource(context: Context) : ZooDataSource {
    override suspend fun getProperties(): Result<List<ResultX>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAnimalsResult(): Result<List<ResultXXX>> {
        TODO("Not yet implemented")
    }

}
