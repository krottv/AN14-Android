package com.github.krottv.tmstemp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.ViewModelProviders
import kotlin.properties.Delegates

private const val EXTRA_NAME = "Name of student"
private const val KEY_IS_TRANSFORMED = "Is name transformed key"
private const val KEY_NAME = "Current name"
const val EXTRA_IS_NAME_TRANSFORMED = "Is name transformed extra"

class TransformatorActivity : AppCompatActivity() {

    private val transformatorViewModel by lazy {
        ViewModelProviders.of(this@TransformatorActivity).get(TransformatorViewModel::class.java)
    }

    private lateinit var textView: TextView
    private lateinit var transformButton: Button
    private var transformMessage = "Transformed_"
    private val regEx = "^(Transformed)\\_(?!Transformed_).*"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transformator)

        val isTransformed = savedInstanceState?.getBoolean(KEY_IS_TRANSFORMED, false) ?: false
        transformatorViewModel.setIsTransformed(isTransformed)
        val currentName = savedInstanceState?.getString(KEY_NAME, "Null") ?: "Null"
        transformatorViewModel.setText(currentName)

        val textName = intent.getStringExtra(EXTRA_NAME)
        if (textName != null) {
            transformatorViewModel.setText(textName)
        }
        transformButton = findViewById(R.id.transform_button)
        textView = findViewById(R.id.text_transformator)

        transformButton.setOnClickListener {
            transformText()
            updateScreen()
        }

        updateScreen()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString(KEY_NAME, transformatorViewModel.getText())
        savedInstanceState.putBoolean(KEY_IS_TRANSFORMED, transformatorViewModel.getIsTransformed())
    }

    companion object{
        fun newIntent(context: Context, name: String): Intent{
            return Intent(context, TransformatorActivity::class.java).apply {
                putExtra(EXTRA_NAME, name)
            }
        }
    }

    private fun sendResult(){
        val data = Intent().apply {
            putExtra(EXTRA_IS_NAME_TRANSFORMED, transformatorViewModel.getIsTransformed())
        }

        setResult(Activity.RESULT_OK, data)
    }

    private fun updateScreen(){
        textView.setText(transformatorViewModel.getText())
        sendResult()
    }

    private fun transformText(){
        if (!(transformatorViewModel.getText().matches(regEx.toRegex()))) {
            transformatorViewModel.setText(transformMessage + transformatorViewModel.getText())
            transformatorViewModel.setIsTransformed(true)
        } else {
            val toastMessage = R.string.already_transformed_toast
            Toast.makeText(this, toastMessage, LENGTH_SHORT).show()
        }
    }
}