package com.gemidroid.comicshow.data.models

import com.google.gson.annotations.SerializedName

data class ErrorBody(var success: Boolean, var errors: List<Error>, var message: String)

data class Error(var value: String, @SerializedName("msg") var message: String, var param : String, var location: String)
