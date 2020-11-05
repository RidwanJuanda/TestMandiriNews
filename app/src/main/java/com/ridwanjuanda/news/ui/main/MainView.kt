package com.ridwanjuanda.news.ui.main

import com.ridwanjuanda.news.base.BaseView
import com.ridwanjuanda.news.models.Article

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
interface MainView : BaseView {
    fun onSuccessArticle(data: List<Article>)
}