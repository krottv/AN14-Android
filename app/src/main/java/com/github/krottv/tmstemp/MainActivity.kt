package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.Circle
import com.tms.android.ffthtask.AnimationListenerEndView
import com.tms.android.ffthtask.MessageFragment

class MainActivity : AppCompatActivity() {

    private lateinit var deleteButton: Button
    private lateinit var progressBar: SpinKitView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.frame1)

        if (currentFragment == null) {
            val fragment = MessageFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.frame1, fragment).commit()
        }

        progressBar = findViewById(R.id.spin_kit)
        progressBar.setIndeterminateDrawable(Circle())
        val alphaAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)
        progressBar.animation = alphaAnim

        alphaAnim.setAnimationListener(AnimationListenerEndView(progressBar))

        deleteButton = findViewById(R.id.delete_button)
        deleteButton.setOnClickListener{
            val fragment: MessageFragment = supportFragmentManager.findFragmentById(R.id.frame1) as MessageFragment
            if (!fragment.checkEmpty()) {
                fragment.deleteOne()
            } else {
                Toast.makeText(this, "List is empty :(", Toast.LENGTH_SHORT).show()
            }
        }
    }
}