package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.CircleCropTransformation

class MainActivity : AppCompatActivity() {

    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var lay1: ConstraintLayout
    private lateinit var lay2: ConstraintLayout
    private lateinit var lay3: ConstraintLayout
    private lateinit var mTxt1: TextView
    private lateinit var mTxt2: TextView
    private lateinit var mTxt3: TextView
    private lateinit var sTxt1: TextView
    private lateinit var sTxt2: TextView
    private lateinit var sTxt3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)

        val img1Link = "https://images.unsplash.com/photo-1568127861543-b0c0696c735f?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        val img2Link = "https://images.unsplash.com/photo-1563452965085-2e77e5bf2607?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"
        val img3Link = "https://images.unsplash.com/photo-1575439047055-83e6174df3b9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=470&q=80"

        img1.load(img1Link){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation())
        }
        img2.load(img2Link){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation())
        }
        img3.load(img3Link){
            crossfade(true)
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation())
        }

        lay1 = findViewById(R.id.constr_1)
        lay2 = findViewById(R.id.constr_2)
        lay3 = findViewById(R.id.constr_3)

        mTxt1 = findViewById(R.id.m_txt_1)
        mTxt1.setText(R.string.m_txt_1)

        mTxt2 = findViewById(R.id.m_txt_2)
        mTxt2.setText(R.string.m_txt_2)

        mTxt3 = findViewById(R.id.m_txt_3)
        mTxt3.setText(R.string.m_txt_3)

        sTxt1 = findViewById(R.id.s_txt_1)
        sTxt1.setText(R.string.some_txt)

        sTxt2 = findViewById(R.id.s_txt_2)
        sTxt2.setText(R.string.some_txt)

        sTxt3 = findViewById(R.id.s_txt_3)
        sTxt3.setText(R.string.some_txt)

        val scaleAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.scale_infinite)
        lay1.animation = scaleAnim

        val alphaAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.alpha_infinite)
        lay3.animation = alphaAnim

        lay1.setOnClickListener{
            val intent = SecondScreenActivity.newIntent(this@MainActivity, mTxt1.text.toString(), sTxt1.text.toString(), img1Link)
            startActivity(intent)
        }

        lay2.setOnClickListener{
            val intent = SecondScreenActivity.newIntent(this@MainActivity, mTxt2.text.toString(), sTxt2.text.toString(), img2Link)
            startActivity(intent)
        }

        lay3.setOnClickListener{
            val intent = SecondScreenActivity.newIntent(this@MainActivity, mTxt3.text.toString(), sTxt3.text.toString(), img3Link)
            startActivity(intent)
        }
    }
}