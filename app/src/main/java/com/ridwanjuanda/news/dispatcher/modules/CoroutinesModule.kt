package com.ridwanjuanda.news.dispatcher.modules

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

import com.ridwanjuanda.news.dispatcher.DefaultDispatcher
import com.ridwanjuanda.news.dispatcher.IoDispatcher
import com.ridwanjuanda.news.dispatcher.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object CoroutinesModule {

    @DefaultDispatcher
    @JvmStatic
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @JvmStatic
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @JvmStatic
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}