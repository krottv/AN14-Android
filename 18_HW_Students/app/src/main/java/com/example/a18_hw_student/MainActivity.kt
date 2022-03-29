package com.example.a18_hw_student
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val students: List<Student> = listOf(
        Student(10, 3, "Anton"),
        Student(11, 3, "Kate"),
        Student(10, 4, "Miron"),
        Student(10, 4, "Lena"),
        Student(12, 4, "Nikita"),
        Student(12, 5, "Alex"),
        Student(12, 6, "Mark"),
        Student(13, 6, "Kirill"),
        Student(11, 3, "Vlad"),
        Student(10, 2, "Peter"),
    )
    private var student: Student = Student(10, 3, "Anton")
        @SuppressLint("SetTextI18n")
        set(value) {
            field = value
            textView.text = " ${value.name}"
        }

    private lateinit var secondActivityStarter: ActivityResultLauncher<Student>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
        textView.text = "nobody"

        val button: Button = findViewById(R.id.nextStudent)
        var studentPosition = 0
        button.setOnClickListener {

            student = students[studentPosition]
            studentPosition++
            if (studentPosition == students.size) {
                studentPosition = 0
            }
        }

        val buttonGoToSecond: Button = findViewById(R.id.transformStudent)
        buttonGoToSecond.setOnClickListener {
            secondActivityStarter.launch(student)
        }

        if (savedInstanceState != null) {
            student = savedInstanceState
                .getParcelable("student")!!
        }

        secondActivityStarter = registerForActivityResult(ActivityContractNextStudent()) {
            if (it != null) {
                student = it
            }
        }

        Log.i("tms-activity", "My Activity Has Started!")
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("student", student)
    }
}

class ActivityContractNextStudent : ActivityResultContract<Student, Student?>() {

    override fun createIntent(context: Context, input: Student): Intent {
        return Intent(context, TransformStudentActivity::class.java).putExtra("student", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? {
        return intent?.getParcelableExtra("student")
    }
}
