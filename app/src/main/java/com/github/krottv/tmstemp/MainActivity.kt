package com.github.krottv.tmstemp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {




    lateinit var infoStudent: TextView
    var students: MutableList<Student> =
        listOf<Student>(Student(20, 5, "Vadim"),
            Student(25, 3, "Sergei"),
            Student(40, 5,"Petya"),
            Student(34, 7,"Alexandr"),
            Student(25, 2,"Kolya"),
            Student(28, 1,"Oleg"),
            Student(56, 14,"Dima"),
            Student(25, 3,"Andrey"),
            Student(32, 5,"Pavel"),
            Student(25, 5,"Denis"),) as MutableList<Student>
    var count = 0
        set(value) {
            field = value
            infoStudent.text = students[value].toString()
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoStudent = findViewById(R.id.info_about_student)

        val nextStudent: Button = findViewById(R.id.next_student)
        val transformStudent: Button = findViewById(R.id.transform_student_main)
        val result = registerForActivityResult(SecondActivityContract()) {
            students[count] = it!!
            infoStudent.text = it.toString()
        }

        nextStudent.setOnClickListener { infoStudent.text = students[++count].toString() }
        transformStudent.setOnClickListener {
            result.launch(students[count])
        }


    }
}
class SecondActivityContract : ActivityResultContract<Student, Student?>() {

    override fun createIntent(context: Context, student: Student?): Intent {
        return Intent(context, TransformStudentActivity::class.java).putExtra("student", student)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? = when {
        resultCode != Activity.RESULT_OK -> null
        else -> intent?.getParcelableExtra("student")
    }
}