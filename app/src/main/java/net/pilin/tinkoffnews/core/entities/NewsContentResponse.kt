package net.pilin.tinkoffnews.core.entities

import com.google.gson.annotations.SerializedName

data class NewsContentResponse(
    @SerializedName("resultCode")
    var resultCode: String? = null,

    @SerializedName("payload")
    var payload: NewsContentEntity? = null,

    @SerializedName("trackingId")
    var trackingId: String? = null
)


