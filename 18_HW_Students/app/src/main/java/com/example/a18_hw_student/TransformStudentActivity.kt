package com.example.a18_hw_student

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TransformStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_transform)

        val student: Student = intent.getParcelableExtra("student")!!
        val studentNew = student.copy(name = "transformed_${student.name}")

        val button: Button = findViewById(R.id.buttonTransform)
        button.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("student", studentNew))
            finish()
        }

    }

}