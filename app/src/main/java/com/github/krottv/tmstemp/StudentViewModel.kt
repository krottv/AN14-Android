package com.github.krottv.tmstemp

import androidx.lifecycle.ViewModel

class StudentViewModel: ViewModel() {

    private var currentIndex = 0
    private var transformMessage = "Transformed_"

    private val studentList = listOf(
        Student("John"),
        Student("Mike"),
        Student("Brad"),
        Student("Nick"),
        Student("Bob"),
        Student("Tim"),
        Student("Corey"),
        Student("Eric"),
        Student("Hanna"),
        Student("Jim"))

    fun getName(): String{
        return studentList[currentIndex].name
    }

    fun setNameList(array: ArrayList<String>){
        for (indexInArray in array.indices){
            studentList[indexInArray].name = array[indexInArray]
        }
    }

    fun getNameList(): ArrayList<String>{
        val array = arrayListOf<String>()
        for (index in studentList.indices){
            array.add(studentList[index].name)
        }
        return array
    }

    fun implementCI(){
        currentIndex = (currentIndex + 1) % studentList.size
    }

    fun getIndex(): Int{
        return currentIndex
    }

    fun setIndex(value: Int){
        currentIndex = value
    }

    private fun transformName(){
        studentList[currentIndex].name = transformMessage + studentList[currentIndex].name
    }

    fun setName(){
        transformName()
    }
}