package com.example.app.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import com.example.app.data.remote.RemoteDataSourceRetrofit
import com.example.app.presentation.view.DownloadNotification
import okhttp3.ResponseBody
import java.io.FileOutputStream
import java.io.InputStream

class UploadMusicWorker(
    private val appContext: Context,
    params: WorkerParameters,

) : CoroutineWorker(appContext, params) {

    private val downloadNotification: DownloadNotification = DownloadNotification(appContext)
    private val remoteDataSourceRetrofit = RemoteDataSourceRetrofit()

    override suspend fun doWork(): Result {

        setForeground(getForegroundInfo())
        val url = inputData.getString("1")
        val fileName = url!!.substring(url.lastIndexOf("/") + 1)
        val pathWhereYouWantToSaveFile = appContext.filesDir.absolutePath + fileName
        val responseBody = url.let { remoteDataSourceRetrofit.downloadFile(it).body() }
        saveFile(responseBody, pathWhereYouWantToSaveFile)

        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(9274, downloadNotification.downloadNotification())
    }

    private fun saveFile(body: ResponseBody?, pathWhereYouWantToSaveFile: String): String {
        if (body == null)
            return ""
        var input: InputStream? = null
        try {
            input = body.byteStream()
            val fos = FileOutputStream(pathWhereYouWantToSaveFile)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return pathWhereYouWantToSaveFile
        } catch (e: Exception) {
            Log.e("saveFile", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }

}