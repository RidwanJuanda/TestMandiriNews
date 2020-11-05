package com.ridwanjuanda.news.dispatcher.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ridwanjuanda.news.ui.article.ArticleViewModel
import com.ridwanjuanda.news.ui.detailarticle.DetailArticleViewModel
import com.ridwanjuanda.news.ui.main.MainViewModel
import com.ridwanjuanda.news.ui.search.SearchViewModel
import com.ridwanjuanda.news.ui.source.SourceViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailArticleViewModel::class)
    abstract fun bindDetailArticleVM(detailArticleViewModel: DetailArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchVM(searchViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainVM(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleViewModel::class)
    abstract fun bindArticleVM(articleViewModel: ArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SourceViewModel::class)
    abstract fun bindSourceVM(searchHistoryViewModel: SourceViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}