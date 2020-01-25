package net.pilin.tinkoffnews.core.entities

import com.google.gson.annotations.SerializedName

data class NewsTitleEntity(
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("text")
    var text: String? = null,

    @SerializedName("publicationDate")
    var publicationDate: MsTimeEntity? = null,

    @SerializedName("bankInfoTypeId")
    var bankInfoTypeId: Long? = null

)
