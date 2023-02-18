package com.example.wetterbericht.helpers

import android.content.Context
import android.net.ConnectivityManager
import java.text.SimpleDateFormat
import java.util.*
import kotlin.streams.asSequence

object ConstantTask {
    var formatTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)
    var formatDate = SimpleDateFormat("dd-MM-yyy", Locale.getDefault())
    var formatDay = SimpleDateFormat("dd", Locale.getDefault())
    var formatMonth = SimpleDateFormat("MM", Locale.getDefault())
    var formatYear  = SimpleDateFormat("yyy", Locale.getDefault())

    private const val idSources = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var userId = Random().ints(10, 0, idSources.length)
        .asSequence()
        .map(idSources::get)
        .joinToString("")

    var todoId = (0..100000).random()

    fun internetChecker(context: Context): Boolean{
        val connected : Boolean
        val connectionManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val networkInfo = connectionManager.activeNetworkInfo
        connected = networkInfo != null && networkInfo.isConnected
        return connected
    }
}