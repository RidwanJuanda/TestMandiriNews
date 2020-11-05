package com.ridwanjuanda.news.dispatcher.modules

import android.app.Application
import android.content.Context
import com.ridwanjuanda.news.dispatcher.viewmodels.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

@Module(includes = [ViewModelModule::class])
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }

}