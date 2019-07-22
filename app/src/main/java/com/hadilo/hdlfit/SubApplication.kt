package com.hadilo.hdlfit

import android.app.Application
import com.backendless.Backendless

/**
 * Created by Hadilo Muhammad on 2019-07-23.
 */
class SubApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initBackendless()
    }

    fun initBackendless() {
        val applicationId = "4024188E-B308-D731-FFA6-15D1E5202700"
        val apiKey = "AE677F98-7753-1E9C-FFAB-6B39A9484F00"
        Backendless.initApp( applicationContext, applicationId, apiKey)
    }
}