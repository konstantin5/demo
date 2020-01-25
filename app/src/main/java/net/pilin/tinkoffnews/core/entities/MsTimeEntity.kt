package net.pilin.tinkoffnews.core.entities

import com.google.gson.annotations.SerializedName

data class MsTimeEntity (
    @SerializedName("milliseconds")
    var milliseconds: Long = 0L
)
