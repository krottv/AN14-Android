package  com.example.homework

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TransformStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transform_student)

        var student = intent.getParcelableExtra<Student>("student")
        var infoAboutStudent: TextView = findViewById(R.id.info_transform_student)
        val transformStudent: Button = findViewById(R.id.transform_student)

        infoAboutStudent.text = student.toString()

        transformStudent.setOnClickListener {
            student?.name = "transform_${student?.name}"
            setResult(RESULT_OK, Intent().putExtra("student", student))
            finish()
        }
    }
}