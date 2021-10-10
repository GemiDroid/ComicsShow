package com.gemidroid.comicshow.data.repository

import com.gemidroid.comicshow.data.models.Comic
import kotlinx.coroutines.flow.Flow

interface IComicsRepository {
    suspend fun getAllComics(): Flow<Comic>
    suspend fun getComicById(id: String): Flow<Comic>
}
