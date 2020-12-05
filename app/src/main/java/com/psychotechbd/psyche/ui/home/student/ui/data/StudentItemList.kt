package com.psychotechbd.psyche.ui.home.student.ui.data

data class StudentItemList(
    val name: String,
    val id: String,
    val session: String,
    val imageUrl: String,
) {
    constructor() : this( "", "", "", "")
}
