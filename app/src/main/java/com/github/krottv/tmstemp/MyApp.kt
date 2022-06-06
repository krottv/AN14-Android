package com.github.krottv.tmstemp

import android.app.Application
import androidx.room.Room
import com.github.krottv.tmstemp.data.RemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.db.AlbumDbDataSource
import com.github.krottv.tmstemp.data.db.AlbumsRoomDataSource
import com.github.krottv.tmstemp.data.remote.AlbumRemoteDataSource
import com.github.krottv.tmstemp.data.remote.AlbumsMyMusicDataSource
import com.github.krottv.tmstemp.data.remote.ITunesRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.remote.LibraryRemoteDataSourceRetrofit
import com.github.krottv.tmstemp.data.room.MyDatabase
import com.github.krottv.tmstemp.presentation.AlbumsViewModel
import com.github.krottv.tmstemp.presentation.TracksViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

class MyApp : Application() {

   /* private val moduleDatasource: Module
        get() = module {

            factory<AlbumRemoteDataSource> { AlbumsMyMusicDataSource(get()) }
            single<AlbumDbDataSource> { AlbumsRoomDataSource(get()) }

            single {

                Room.databaseBuilder(
                    get(),
                    MyDatabase::class.java, "database-name"
                ).build()
            }
        }*/

    private val itunesModule: Module
        get() = module {
            factoryOf<RemoteDataSourceRetrofit>(::ITunesRemoteDataSourceRetrofit)
        }

    private val libraryModule: Module
        get() = module {
            factoryOf<RemoteDataSourceRetrofit>(::LibraryRemoteDataSourceRetrofit)
        }

    private val viewModelModule: Module
        get() = module {
            viewModelOf(::AlbumsViewModel)
            viewModelOf(::TracksViewModel)
        }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                //moduleDatasource,
                libraryModule, itunesModule, viewModelModule)
        }
    }
}
