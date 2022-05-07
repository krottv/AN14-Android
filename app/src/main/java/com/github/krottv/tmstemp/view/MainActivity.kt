package com.github.krottv.tmstemp.view
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.krottv.tmstemp.binder.MainActivityDataBinder
import com.github.krottv.tmstemp.binder.MainActivityRecyclerScrollDataBinder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityDataBinder: MainActivityDataBinder = MainActivityRecyclerScrollDataBinder(this)
        mainActivityDataBinder.bind()
    }
}