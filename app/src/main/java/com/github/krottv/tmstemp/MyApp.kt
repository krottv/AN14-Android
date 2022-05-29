package com.github.krottv.tmstemp

import android.app.Application
import com.github.krottv.tmstemp.data.remote.*
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.MyMusicViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MyApp : Application() {

    private val itunesModule: Module
        get() = module {
            factory<MusicApi> { ITunesRemoteDataSourceRetrofit() }
        }

    private val libraryModule: Module
        get() = module {
            factory<MusicApi> { LibraryRemoteDataSourceRetrofit() }
        }

    private val myMusicModule: Module
        get() = module {
            factory<MusicApi> { MyMusicRemoteDataSourceRetrofit() }
        }

    private val viewModelModule: Module
        get() = module {
            viewModel { AlbumsViewModel(get()) }
            viewModel { TracksViewModel(get()) }
            viewModel { MyMusicViewModel(get()) }
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(libraryModule, itunesModule, viewModelModule, myMusicModule)
        }
    }
}