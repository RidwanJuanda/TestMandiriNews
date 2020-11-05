package com.ridwanjuanda.news.network

import com.ridwanjuanda.news.dispatcher.IoDispatcher
import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.ResultData
import com.ridwanjuanda.news.models.SourceResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

class RemoteDataSource(
    private val api: RetrofitInterface,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher)
    : AppRepository {

    override suspend fun getSourceByCategory2(category: String): ResultData<SourceResponse>  =
        withContext(ioDispatcher) {
            val request = api.getSourceByCategory2(category)
            ResultData.Success(request)
        }

    override suspend fun getArticleByCategory2(source: String, page: Int): ResultData<ArticleResponse> =
        withContext(ioDispatcher) {
            val request = api.getArticleBySource2(source, page)
            ResultData.Success(request)
        }

    override suspend fun getSearchArticle2(text: String) : ResultData<ArticleResponse> =
        withContext(ioDispatcher) {
            val request = api.getSearchArticle2(text)
            ResultData.Success(request)
        }


}