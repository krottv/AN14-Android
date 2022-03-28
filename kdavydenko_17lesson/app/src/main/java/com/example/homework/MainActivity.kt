package com.example.homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.example.homework.R

class MainActivity : AppCompatActivity() {

    lateinit var infoAboutStudent: TextView
    var students: MutableList<Student> =
        listOf<Student>(Student(10, 30, "Kolya"), Student(20, 30, "Oleg")) as MutableList<Student>
    var increment = 0
        set(value) {
            field = value
            infoAboutStudent.text = students[value].toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoAboutStudent = findViewById(R.id.info_about_student)

        val nextStudent: Button = findViewById(R.id.next_student)
        val transformStudent: Button = findViewById(R.id.transform_student_main_activity)

        infoAboutStudent.text = students[increment].toString()

        val result = registerForActivityResult(MySecondActivityContract()) {
            students[increment] = it!!
            infoAboutStudent.text = it.toString()
        }

        nextStudent.setOnClickListener { infoAboutStudent.text = students[++increment].toString() }

        transformStudent.setOnClickListener {
            result.launch(students[increment])
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("increment", increment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        increment = savedInstanceState.getInt("increment")
    }
}

class MySecondActivityContract : ActivityResultContract<Student, Student?>() {

    override fun createIntent(context: Context, input: Student?): Intent {
        return Intent(context, TransformStudentActivity::class.java).putExtra("student", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getParcelableExtra("student")
    }
}