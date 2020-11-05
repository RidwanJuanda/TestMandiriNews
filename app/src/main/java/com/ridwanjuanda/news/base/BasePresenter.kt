package com.ridwanjuanda.news.base

import com.ridwanjuanda.news.network.RetrofitInterface
import com.ridwanjuanda.news.network.URLManager

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
open class BasePresenter<V> {
    var view: V? = null
    var retrofitInterface: RetrofitInterface = URLManager.getRetrofit().create(RetrofitInterface::class.java)

    val isViewNotNull: Boolean
        get() = view != null

    fun attachView(view: V) {
        this.view = view
    }

    fun dettachView() {
        this.view = null
    }
}