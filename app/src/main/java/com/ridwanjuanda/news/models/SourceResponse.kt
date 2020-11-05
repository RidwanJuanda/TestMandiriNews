package com.ridwanjuanda.news.models

import com.google.gson.annotations.SerializedName

/**
 * @author Ridwan Juanda
 * @date 04/10/20
 */

class SourceResponse {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("sources")
    var source: List<Source>? = null
}

class Source {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("category")
    var category: String? = null

}