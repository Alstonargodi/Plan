package com.example.wetterbericht.helpers.sortfilter

import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder

object SortUtils {
    fun getSortedQuery(filter : HabitSortType): SimpleSQLiteQuery{
        val simpleQuery = StringBuilder().append("SELECT * FROM habitstable ")
        when(filter){
            HabitSortType.START_TIME->{
                simpleQuery.append("ORDER BY time(startTime) ASC")
            }
            HabitSortType.MINUTE_FOCUS->{
                simpleQuery.append("ORDER BY minuteFocus ASC")
            }
            HabitSortType.TITLE_NAME->{
                simpleQuery.append("ORDER BY title ASC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}