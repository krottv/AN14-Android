package com.github.krottv.tmstemp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.CycleInterpolator
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import coil.load

private const val EXTRA_MAIN_TEXT = "main txt"
private const val EXTRA_SUB_TEXT = "sub txt"
private const val EXTRA_IMAGE_LINK = "img link"

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var mainText: TextView
    private lateinit var subText: TextView
    private lateinit var img: ImageView
    private lateinit var crossButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        mainText = findViewById(R.id.txt_ss_m)
        val mTxt = intent.getStringExtra(EXTRA_MAIN_TEXT)
        if (mTxt != null) {
            mainText.setText(mTxt)
        }

        subText = findViewById(R.id.txt_ss_s)
        val sTxt = intent.getStringExtra(EXTRA_SUB_TEXT)
        if (sTxt != null){
            subText.setText(sTxt)
        }

        img = findViewById(R.id.img_ss)
        val imgLink = intent.getStringExtra(EXTRA_IMAGE_LINK)
        if (imgLink != null){
            img.load(imgLink)
        }

        crossButton = findViewById(R.id.cross_button)
        val crossAnimate: Animation = AnimationUtils.loadAnimation(this, R.anim.rotation)
        crossButton.animation = crossAnimate
//        crossButton.animate().apply {
//            rotation(3600F)
//            duration = 7500
//            interpolator = AccelerateDecelerateInterpolator()
//        }.start()
        crossButton.setOnClickListener{
            finish()
        }
    }

    companion object{
        fun newIntent(context: Context, mainText: String, subText: String, link: String): Intent{
            return Intent(context, SecondScreenActivity::class.java).apply {
                putExtra(EXTRA_MAIN_TEXT, mainText)
                putExtra(EXTRA_SUB_TEXT, subText)
                putExtra(EXTRA_IMAGE_LINK, link)
            }
        }
    }
}