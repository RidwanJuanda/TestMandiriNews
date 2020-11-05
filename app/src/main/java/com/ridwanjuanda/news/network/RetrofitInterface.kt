package com.ridwanjuanda.news.network

import com.ridwanjuanda.news.network.URLConfig.API_KEY
import com.ridwanjuanda.news.models.ArticleResponse
import com.ridwanjuanda.news.models.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

interface RetrofitInterface {

    @GET("/v2/sources")
    fun getSourceByCategory(@Query("category") category: String,
                            @Query("apiKey") apiKey: String = API_KEY) : Call<SourceResponse>

    @GET("/v2/everything")
    fun getArticleBySource(@Query("sources") sources: String,
                           @Query("page") page: Int,
                           @Query("pageSize") pageSize: Int = 10,
                           @Query("apiKey") apiKey: String = API_KEY) : Call<ArticleResponse>

    @GET("/v2/everything")
    fun getSearch(@Query("q") q: String,
                  @Query("apiKey") apiKey: String = API_KEY) : Call<ArticleResponse>

    @GET("/v2/sources")
    suspend fun getSourceByCategory2(@Query("category") category: String,
                                     @Query("apiKey") apiKey: String = API_KEY): SourceResponse

    @GET("/v2/everything")
    suspend fun getArticleBySource2(@Query("sources") sources: String,
                           @Query("page") page: Int,
                           @Query("pageSize") pageSize: Int = 10,
                           @Query("apiKey") apiKey: String = API_KEY) : ArticleResponse

    @GET("/v2/everything")
    suspend fun getSearchArticle2(@Query("q") q: String,
                  @Query("apiKey") apiKey: String = API_KEY) : ArticleResponse
}