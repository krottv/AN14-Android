package com.github.krottv.tmstemp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    var students = listOf(Student(18,1,"Lex"),
        Student(17,0,"Clark"),
        Student(22,5,"Lana"),
        Student(21,4,"John"),
        Student(19,3,"Peter"),
        Student(18,1,"Chloe"),
        Student(18,2,"Martha"),
        Student(17,0,"Jonatan"),
        Student(25,4,"Valery"),
        Student(20,3,"Spencer")) as MutableList
    var numberOfStudent : Int = 0
    set(value) {
        field = value
        textView.text = "${students[value].name}"
    }


    lateinit var transformActivityStarter: ActivityResultLauncher<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView.text = students[numberOfStudent].name

        val buttonNext= findViewById<Button>(R.id.nextStudent)
        buttonNext.setOnClickListener {
            if (numberOfStudent >= students.size-1) numberOfStudent = 0
            else numberOfStudent++
        }

        val buttonTransform = findViewById<Button>(R.id.transformStudent)
        buttonTransform.setOnClickListener { transformActivityStarter.launch(students[numberOfStudent])}

        transformActivityStarter = registerForActivityResult(ActivityContractStudents()) {
            if (it != null) {
                students[numberOfStudent] = it
                textView.text = it.name
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("numberOfStudent", numberOfStudent)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        numberOfStudent = savedInstanceState.getInt("numberOfStudent")
    }
}