package com.github.krottv.tmstemp

import androidx.lifecycle.ViewModel

class TransformatorViewModel: ViewModel() {

    private var text: String = ""
    private var isTransformed = false
    private val regEx: Regex = "^(Transformed)\\_.*".toRegex()

    fun getText(): String{
        return text
    }

    fun setText(value: String){
        if (!text.matches(regEx)) {
            text = value
        }
    }

    fun getIsTransformed(): Boolean{
        return isTransformed
    }

    fun setIsTransformed(value: Boolean){
        isTransformed = value
    }
}