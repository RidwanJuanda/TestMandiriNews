package com.ridwanjuanda.news.ui.article

import com.ridwanjuanda.news.base.BaseView
import com.ridwanjuanda.news.models.Article

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
interface ArticleView : BaseView {
    fun onSuccessArticle(data: List<Article>)
    fun onLoadMoreLoading()
    fun onLoadMoreError()
    fun onDoneLoadMore()
}