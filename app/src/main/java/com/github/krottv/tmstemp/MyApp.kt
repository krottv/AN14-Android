package com.github.krottv.tmstemp

import android.app.Application
import com.github.krottv.tmstemp.data.AlbumsRepository
import com.github.krottv.tmstemp.data.SongsRepository
import com.github.krottv.tmstemp.data.db.AlbumDbDataSource
import com.github.krottv.tmstemp.data.db.AlbumDbInMemoryDataSource
import com.github.krottv.tmstemp.data.db.SongDbDataSource
import com.github.krottv.tmstemp.data.db.SongDbInMemoryDataSource
import com.github.krottv.tmstemp.data.remote.AlbumRemoteDataSource
import com.github.krottv.tmstemp.data.remote.AlbumRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.remote.SongRemoteDataSource
import com.github.krottv.tmstemp.data.remote.SongRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.presentation.AlbumViewModel
import com.github.krottv.tmstemp.presentation.SongViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApp : Application() {

    private val moduleRepository: Module
        get() = module {
            factory { AlbumsRepository(get(), get()) }
            factory { SongsRepository(get(), get(), get()) }
            factory<SongDbDataSource> { SongDbInMemoryDataSource() }
            factory<SongRemoteDataSource> { SongRemoteDataSourceRetrofit() }
            factory<AlbumDbDataSource> { AlbumDbInMemoryDataSource() }
            factory<AlbumRemoteDataSource> { AlbumRemoteDataSourceRetrofit() }
            viewModel { AlbumViewModel(get()) }
            viewModel { SongViewModel(get()) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            module {
                modules(moduleRepository)
                androidContext(this@MyApp)
            }
        }
    }
}