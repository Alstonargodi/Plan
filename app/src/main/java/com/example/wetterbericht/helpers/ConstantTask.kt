package com.example.wetterbericht.helpers

import java.text.SimpleDateFormat
import java.util.*
import kotlin.streams.asSequence

object ConstantTask {
    var formatTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    var formatDate = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
    var formatDay = SimpleDateFormat("dd", Locale.getDefault())

    private const val idSources = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var userId = Random().ints(10, 0, idSources.length)
        .asSequence()
        .map(idSources::get)
        .joinToString("")
}