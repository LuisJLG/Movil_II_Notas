package com.example.juicetracker.contexto

import android.app.Application
import android.content.Context

object  contecsto{
    private lateinit var appContext: Context

    fun initialize(application: Application) {
        appContext = application.applicationContext
    }

    fun getApplicationContext(): Context {
        return appContext
    }
}