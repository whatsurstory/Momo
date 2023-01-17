package com.beva.momoapplication

import android.app.Application
import com.beva.momoapplication.source.ServiceLocator
import com.beva.momoapplication.source.ZooRepository
import kotlin.properties.Delegates

class ZooApplication : Application() {

    // Depends on the flavor,
    val zooRepository: ZooRepository
        get() = ServiceLocator.provideRepository(this)

    companion object {
        var instance: ZooApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}