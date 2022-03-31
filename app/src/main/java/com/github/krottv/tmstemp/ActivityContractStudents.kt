package com.github.krottv.tmstemp

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract

class ActivityContractStudents: ActivityResultContract<Student, Student?>() {

    override fun createIntent(context: Context, input: Student): Intent {
        return Intent(context, TransformActivity::class.java).putExtra("student", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? {
        return intent?.getParcelableExtra("student")
    }
}