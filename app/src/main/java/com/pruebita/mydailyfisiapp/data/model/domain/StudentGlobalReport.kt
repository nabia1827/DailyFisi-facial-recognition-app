package com.pruebita.mydailyfisiapp.data.model.domain

data class StudentGlobalReport(
    var assSd: MutableList<DailyCourseAssist> = mutableListOf(),
    var assMet: MutableList<DailyCourseAssist> = mutableListOf(),
    var assIng: MutableList<DailyCourseAssist> = mutableListOf(),
    var assSi: MutableList<DailyCourseAssist> = mutableListOf(),
    var assGdp: MutableList<DailyCourseAssist> = mutableListOf(),
    var assAudi: MutableList<DailyCourseAssist> = mutableListOf(),
    var assMoviles: MutableList<DailyCourseAssist> = mutableListOf(),
    var assCalculo: MutableList<DailyCourseAssist> = mutableListOf(),
    var assMkt: MutableList<DailyCourseAssist> = mutableListOf()
)
