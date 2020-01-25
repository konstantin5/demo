package net.pilin.tinkoffnews.core.entities

import com.google.gson.annotations.SerializedName

class NewsContentEntity(
    @SerializedName("title")
    var title: NewsTitleEntity? = null,

    @SerializedName("creationDate")
    var creationDate: MsTimeEntity? = null,

    @SerializedName("lastModificationDate")
    var lastModificationDate: MsTimeEntity? = null,

    @SerializedName("content")
    var content: String? = null,

    @SerializedName("bankInfoTypeId")
    var bankInfoTypeId: Long? = null,

    @SerializedName("typeId")
    var typeId: String? = null
)

