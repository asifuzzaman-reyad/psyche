package com.psychotechbd.psyche.ui.study.course

class PdfItemList(
    val teacher: String,
    val code: String,
    val lessonNo: String,
    val lessonTopic: String,
    val fileUrl: String
) {
    constructor() : this("", "","", "", "")
}