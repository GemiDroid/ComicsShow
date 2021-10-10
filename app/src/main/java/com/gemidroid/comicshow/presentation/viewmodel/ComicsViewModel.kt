package com.gemidroid.comicshow.presentation.viewmodel

import AuthenticationState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gemidroid.comicshow.data.handleResponse
import com.gemidroid.comicshow.data.repository.IComicsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ComicsViewModel(private val comicsRepository: IComicsRepository) : ViewModel() {

    private val getAllComics = MutableStateFlow<AuthenticationState>(AuthenticationState.Start)
    val _getAllComics = getAllComics

    init {
        getAllComics()
    }

    private fun getAllComics() {
        viewModelScope.launch {
            handleResponse(comicsRepository.getAllComics(), getAllComics)
        }
    }

    fun getComicById(id: String) {
        viewModelScope.launch {
            handleResponse(comicsRepository.getComicById(id), getAllComics)
        }
    }
}
