package com.github.krottv.tmstemp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TransformStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transform_student)



        val student = intent.getParcelableExtra<Student>("student")
        var infoStudent : TextView = findViewById(R.id.info_transform_student)
        val transformStudent : Button = findViewById(R.id.transform_student)
        infoStudent.text = student.toString()

        transformStudent.setOnClickListener {
            student?.name = "transform_${student?.name}"
            setResult(RESULT_OK, Intent().putExtra("student", student))
            finish()
        }



    }
}