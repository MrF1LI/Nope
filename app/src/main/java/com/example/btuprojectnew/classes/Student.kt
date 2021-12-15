package com.example.btuprojectnew.classes

data class Student (
    var name: String? = null,
    var surname: String? = null,
    var email: String? = null,
    var subjects: ArrayList<Subject>
)