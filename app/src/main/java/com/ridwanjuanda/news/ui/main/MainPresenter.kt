package com.ridwanjuanda.news.ui.main

import com.ridwanjuanda.news.base.BasePresenter
import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.utils.isNotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
class MainPresenter (view: MainView) : BasePresenter<MainView>() {
    init {
        super.attachView(view)
    }

    fun searchArticle(text: String) {
        view?.startLoading()
        val request = retrofitInterface.getSearch(text)
        request.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(call: Call<ArticleResponse>, response: Response<ArticleResponse>) {
                view?.stopLoading()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.status == "ok") {
                        if (isNotNull(responseBody.article)) {
                            view?.onSuccessArticle(responseBody.article!!)
                        } else {
                            view?.showEmpty()
                        }
                        return
                    }
                }
                view?.setErrorMessage()
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                view?.setErrorMessage()
            }
        })
    }

}