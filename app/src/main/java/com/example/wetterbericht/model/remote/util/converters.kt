package com.example.wetterbericht.model.remote.util

import androidx.room.TypeConverter
import java.sql.Date

class converters {
    //todo converter error java.long.date to sql.date

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}