package com.beva.momoapplication.source

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.beva.momoapplication.ZooApplication

object Util {

    /**
     * Determine and monitor the connectivity status
     *
     * https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
     */
    fun isInternetConnected(): Boolean {
        val cm = ZooApplication.instance
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnectedOrConnecting == true
    }

    fun getString(resourceId: Int): String {
        return ZooApplication.instance.getString(resourceId)
    }

//    fun getColor(resourceId: Int): Int {
//        return ZooApplication.instance.getColor(resourceId)
//    }
}