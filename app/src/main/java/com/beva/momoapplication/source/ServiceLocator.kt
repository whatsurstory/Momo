package com.beva.momoapplication.source

import android.content.Context
import androidx.annotation.VisibleForTesting

object ServiceLocator {

    @Volatile
    var repository: ZooRepository? = null
        @VisibleForTesting set

    fun provideRepository(context: Context): ZooRepository {
        synchronized(this) {
            return repository
                ?: repository
                ?: createRepository(context)
        }
    }

    private fun createRepository(context: Context): ZooRepository {
        return DefaultRepository(
            ZooRemoteDataSource,
            createLocalDataSource(context)
        )
    }

    private fun createLocalDataSource(context: Context): ZooDataSource {
        return LocalDataSource(context)
    }

}