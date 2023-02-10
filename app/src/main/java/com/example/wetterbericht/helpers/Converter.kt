package com.example.wetterbericht.helpers

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Converter {

    fun dateParser(currDate : String): String{
        val dateFormat = DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss")
        val date = LocalDate.parse(currDate,dateFormat)
        val time = LocalTime.parse(currDate,dateFormat)
        return "${date.dayOfWeek}, ${time}"
    }

    fun imageUrlParser(icon : String): String{
        return "http://openweathermap.org/img/w/${icon}.png"
    }

}