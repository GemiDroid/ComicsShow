package com.gemidroid.comicshow.di

import com.gemidroid.comicshow.data.datasource.network.IComicsAPI
import com.gemidroid.comicshow.data.repository.ComicsRepositoryImp
import com.gemidroid.comicshow.data.repository.IComicsRepository
import com.gemidroid.comicshow.presentation.viewmodel.ComicsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val comicsModule = module {
    single { get<Retrofit>().create(IComicsAPI::class.java) }
    factory<IComicsRepository> { ComicsRepositoryImp(get()) }
    viewModel { ComicsViewModel(get()) }
}