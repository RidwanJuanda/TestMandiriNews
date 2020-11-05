package com.ridwanjuanda.news.ui.article

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
class ArticlePresenter (view: ArticleView) : BasePresenter<ArticleView>() {
    init {
        super.attachView(view)
    }

    fun getArticleByCategory(source: String, page: Int,  isLoadMore: Boolean) {
        if (isLoadMore) {
            view?.onLoadMoreLoading()
        } else {
            view?.startLoading()
        }
        val request = retrofitInterface.getArticleBySource(source,page)
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
                if (isLoadMore) {
                    if (response.body()?.code == "maximumResultsReached") {
                        view?.onDoneLoadMore()
                    } else {
                        view?.onLoadMoreError()
                    }
                } else {
                    view?.setErrorMessage()
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                if (isLoadMore) {
                    view?.onLoadMoreError()
                } else {
                    view?.setErrorMessage()
                }
            }
        })
    }

}