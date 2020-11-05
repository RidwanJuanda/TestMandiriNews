package com.ridwanjuanda.news.ui.source

import com.ridwanjuanda.news.base.BasePresenter
import com.ridwanjuanda.news.models.SourceResponse
import com.ridwanjuanda.news.utils.isNotNull
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author Ridwan Juanda
 * @date 05/10/20
 */

@Deprecated(message = "Deprecated change to MVVM")
class SourcePresenter (view: SourceView) : BasePresenter<SourceView>() {
    init {
        super.attachView(view)
    }

    fun getSourceByCategory(category: String) {
        view?.startLoading()
        val request = retrofitInterface.getSourceByCategory(category)
        request.enqueue(object : Callback<SourceResponse> {
            override fun onResponse(call: Call<SourceResponse>, response: Response<SourceResponse>) {
                view?.stopLoading()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody?.status == "ok") {
                        if (isNotNull(responseBody.source)) {
                            view?.onSuccessSource(responseBody.source!!)
                        } else {
                            view?.showEmpty()
                        }
                        return
                    }
                }
                view?.setErrorMessage()
            }

            override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                view?.setErrorMessage()
            }
        })
    }

}