package com.ridwanjuanda.news.dispatcher.modules

import com.ridwanjuanda.news.dispatcher.IoDispatcher
import com.ridwanjuanda.news.network.AppRepositoryImpl
import com.ridwanjuanda.news.network.RemoteDataSource
import com.ridwanjuanda.news.network.RetrofitInterface
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
        api: RetrofitInterface
    ): AppRepositoryImpl {
        val userDataSource = RemoteDataSource(api, ioDispatcher)
        return AppRepositoryImpl(userDataSource)
    }
}