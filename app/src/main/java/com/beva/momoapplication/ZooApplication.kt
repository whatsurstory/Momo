package com.beva.momoapplication

import android.app.Application
import com.beva.momoapplication.model.ZooRepository
import kotlin.properties.Delegates

class ZooApplication : Application() {

    // Depends on the flavor,
//    val zooRepository: ZooRepository
//        get() = ServiceLocator.provideTasksRepository(this)

    companion object {
        var instance: ZooApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}