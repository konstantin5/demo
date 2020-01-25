package net.pilin.tinkoffnews.core.entities

import com.google.gson.annotations.SerializedName

data class NewsListResponse (

    @SerializedName("resultCode")
    var resultCode: String? = null,

    @SerializedName("payload")
    var payload: List<NewsEntity>? = null
)
