package com.pruebita.mydailyfisiapp.data.model.domain

data class TeacherGlobalReport(
    var studentList: MutableList<StudentAssistUnit>,
    var theoryAssist: MutableList<Int>,
    var labAssist: MutableList<Int>
)
