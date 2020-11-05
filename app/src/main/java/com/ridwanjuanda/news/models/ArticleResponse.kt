package com.ridwanjuanda.news.models

import com.google.gson.annotations.SerializedName

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

class ArticleResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("totalResults")
    var totalResults: Int? = 0

    @SerializedName("articles")
    var article: List<Article>? = null

}

class Article {

    @SerializedName("author")
    var author: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("publishedAt")
    var publishedAt: String? = null

    @SerializedName("source")
    var source: Source? = null

}

