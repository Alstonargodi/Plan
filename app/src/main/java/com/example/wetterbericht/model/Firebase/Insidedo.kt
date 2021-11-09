package com.example.wetterbericht.model.Firebase

data class Insidedo(
    val title: String,
    val desc: String,
    val kat : String,
    val deadlinedate: String,
    val deadlinetime : String,
    val status: String,
)

data class Subtaskdo(
    val idsub : String,
    val task : String
)