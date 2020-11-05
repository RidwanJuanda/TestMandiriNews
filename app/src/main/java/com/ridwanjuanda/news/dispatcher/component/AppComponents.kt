package com.ridwanjuanda.news.dispatcher.component

import android.content.Context
import com.ridwanjuanda.news.dispatcher.modules.AppModule
import com.ridwanjuanda.news.dispatcher.modules.CoroutinesModule
import com.ridwanjuanda.news.dispatcher.modules.NetworkModule
import com.ridwanjuanda.news.dispatcher.modules.RepositoryModule
import com.ridwanjuanda.news.ui.main.MainActivity
import com.ridwanjuanda.news.ui.article.ArticleFragment
import com.ridwanjuanda.news.ui.category.CategoryFragment
import com.ridwanjuanda.news.ui.detailarticle.DetailArticleActivity
import com.ridwanjuanda.news.ui.search.SearchFragment
import com.ridwanjuanda.news.ui.source.SourceFragment
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        CoroutinesModule::class
    ]
)
interface AppComponents {
    fun context(): Context

    fun retrofit(): Retrofit

    fun inject(mainActivity: MainActivity)
    fun inject(detailArticleActivity: DetailArticleActivity)
    fun inject(sourceFragment: SourceFragment)
    fun inject(categoryFragment: CategoryFragment)
    fun inject(articleFragment: ArticleFragment)
    fun inject(searchFragment: SearchFragment)
}