package com.github.krottv.tmstemp

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders

private const val KEY_CURRENT_INDEX = "Current index value"
private const val KEY_NAME = "Name values"
private const val REQUEST_CODE_TRANSFORM = 0

class MainActivity : AppCompatActivity() {

    private val studentViewModel: StudentViewModel by lazy {
        ViewModelProviders.of(this).get(StudentViewModel::class.java)
    }

    private lateinit var textView: TextView
    private lateinit var nextButton: Button
    private lateinit var transformButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_CURRENT_INDEX, 0) ?: 0
        studentViewModel.setIndex(currentIndex)
        val studentNameList = savedInstanceState?.getStringArrayList(KEY_NAME)
        if (studentNameList != null){
            studentViewModel.setNameList(studentNameList)
        }

        textView = findViewById(R.id.name_of_student)
        nextButton = findViewById(R.id.next_button)
        transformButton = findViewById(R.id.transform_button)

        nextButton.setOnClickListener {
            studentViewModel.implementCI()
            updateScreenText()
        }
        transformButton.setOnClickListener {
            val name = studentViewModel.getName()
            val intent = TransformatorActivity.newIntent(this@MainActivity, name)
            startActivityForResult(intent, REQUEST_CODE_TRANSFORM)
        }

        updateScreenText()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt(KEY_CURRENT_INDEX, studentViewModel.getIndex())
        savedInstanceState.putStringArrayList(KEY_NAME, studentViewModel.getNameList())
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK){
            return
        }

        if (requestCode == REQUEST_CODE_TRANSFORM){
            val requestedBool = data?.getBooleanExtra(EXTRA_IS_NAME_TRANSFORMED, false) ?: false
            if (requestedBool){
                studentViewModel.setName()
                updateScreenText()
            }
        }
    }

    private fun updateScreenText(){
        val textResId = studentViewModel.getName()
        textView.setText(textResId)
    }
}