package com.example.app.data.remote

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.example.app.domain.TrackModel

class TracksMyMusicDataSourceImpl(private val context: Context) :
    TracksMyMusicDataSource {

    private val contentUriTracks: Uri by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Audio.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        }
    }

    override suspend fun getTracks(): List<TrackModel> {
        val contentResolver = context.contentResolver!!

        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            arrayOf(
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media._ID
            ),
            null,
            null,
            MediaStore.Audio.Media.ALBUM + " asc"
        )

        val result = mutableListOf<TrackModel>()
        if (cursor?.moveToFirst() == true) {

            do {
                val artist = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                )
                val id = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)
                )
                val title = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                )

                val image = ContentUris
                    .withAppendedId(contentUriTracks, id)

                result.add(TrackModel(artist, image.toString(), title, ""))

            } while (cursor.moveToNext())
        }
        Log.i("test", result.toString())
        return result
    }
}