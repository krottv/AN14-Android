package com.github.krottv.tmstemp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class TransformActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transform_activity)

        val student: Student = intent.getParcelableExtra("student")!!
        val transformedStudent = Student(student.age, student.experience, "transformed_${student.name}")
        val buttonCreateStudent: Button = findViewById(R.id.createNewStudent)
        buttonCreateStudent.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("student", transformedStudent))
            finish()
        }
        val textView: TextView = findViewById(R.id.textView)

        textView.text = transformedStudent.name
    }
}