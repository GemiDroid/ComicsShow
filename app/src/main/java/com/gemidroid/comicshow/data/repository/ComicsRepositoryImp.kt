package com.gemidroid.comicshow.data.repository

import com.gemidroid.comicshow.data.datasource.network.IComicsAPI
import kotlinx.coroutines.flow.flow

class ComicsRepositoryImp(
    private val comicsAPI: IComicsAPI,
) : IComicsRepository {

    override suspend fun getAllComics() = flow {
        emit(comicsAPI.getAllComics())
    }

    override suspend fun getComicById(id: String) = flow {
        emit(comicsAPI.getComicById(id))
    }
}