package com.ridwanjuanda.news.ui

import android.app.Application
import com.ridwanjuanda.news.dispatcher.component.AppComponents
import com.ridwanjuanda.news.dispatcher.component.DaggerAppComponents
import com.ridwanjuanda.news.dispatcher.modules.AppModule

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

open class MainApplication : Application() {

    companion object {
        lateinit var appComponents: AppComponents
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDagger(this)
    }

    private fun initDagger(app: MainApplication): AppComponents =
        DaggerAppComponents.builder()
            .appModule(AppModule(app))
            .build()
}