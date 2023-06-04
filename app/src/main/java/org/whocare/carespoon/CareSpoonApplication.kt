package org.whocare.carespoon

import android.app.Application
import org.whocare.carespoon.data.CareSpoonSharedPreferences

class CareSpoonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        CareSpoonSharedPreferences.init(applicationContext)
    }
}