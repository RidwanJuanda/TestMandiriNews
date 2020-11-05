package com.ridwanjuanda.news.network

import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.RemoteDataNotFoundException
import com.ridwanjuanda.news.models.ResultData
import com.ridwanjuanda.news.models.SourceResponse

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

class AppRepositoryImpl(private val remoteDataSource: AppRepository) : AppRepository {

    override suspend fun getSourceByCategory2(category: String): ResultData<SourceResponse> {
        return when (val result = remoteDataSource.getSourceByCategory2(category)) {
            is ResultData.Success -> {
                val response = result.data
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }

    override suspend fun getArticleByCategory2(source: String, page: Int): ResultData<ArticleResponse> {
        return when (val result = remoteDataSource.getArticleByCategory2(source, page)) {
            is ResultData.Success -> {
                val response = result.data
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }

    override suspend fun getSearchArticle2(text: String): ResultData<ArticleResponse> {
        return when (val result = remoteDataSource.getSearchArticle2(text)) {
            is ResultData.Success -> {
                val response = result.data
                ResultData.Success(response)
            }
            is ResultData.Error -> {
                ResultData.Error(RemoteDataNotFoundException())
            }
        }
    }


}