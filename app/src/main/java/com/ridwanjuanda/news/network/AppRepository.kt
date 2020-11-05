package com.ridwanjuanda.news.network

import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.ResultData
import com.ridwanjuanda.news.models.SourceResponse

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

interface AppRepository {
    suspend fun getSourceByCategory2(category: String): ResultData<SourceResponse>

    suspend fun getArticleByCategory2(source: String, page: Int): ResultData<ArticleResponse>

    suspend fun getSearchArticle2(text: String): ResultData<ArticleResponse>
}