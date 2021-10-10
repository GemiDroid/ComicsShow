package com.gemidroid.comicshow.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Comic(
    var month: String,
    var num: Int,
    var link: String,
    var year: String,
    var news: String,
    @SerializedName("safe_title")
    var safeTitle: String,
    var transcript: String,
    var alt: String,
    var img: String,
    var title: String,
    var day: String,
    var isLiked: Boolean = true
) : Serializable