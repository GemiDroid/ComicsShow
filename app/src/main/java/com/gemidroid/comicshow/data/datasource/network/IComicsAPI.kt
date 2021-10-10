package com.gemidroid.comicshow.data.datasource.network

import com.gemidroid.comicshow.data.models.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IComicsAPI {

    @GET("info.0.json")
    suspend fun getAllComics(): Comic

    @GET("{id}/info.0.json")
    suspend fun getComicById(@Path("id") id: String): Comic
}
