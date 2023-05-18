package org.joyroom.carespoon

import android.app.Application
import org.joyroom.carespoon.data.CareSpoonSharedPreferences

class CareSpoonApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        CareSpoonSharedPreferences.init(applicationContext)
    }
}