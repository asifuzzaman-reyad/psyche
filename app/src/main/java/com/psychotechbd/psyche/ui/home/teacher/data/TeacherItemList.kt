package com.psychotechbd.psyche.ui.home.teacher.data

data class TeacherItemList(
    val name: String,
    val post: String,
    val phd: String,
    val facebook: String,
    val email: String,
    val mobile: String,
    val publication: String,
    val interest: String,
    val imageUrl: String,
) {
    constructor() : this(
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}