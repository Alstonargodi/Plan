package com.example.wetterbericht.helpers.sortfilter

import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import java.lang.StringBuilder
import java.time.LocalDateTime

object SortUtils {
    //TODO 4 sort type
    fun getSortedQueryHabits(filter : HabitSortType): SimpleSQLiteQuery{
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

    fun getFilterQueryTodo(filter : TodoSortType): SimpleSQLiteQuery{
        val date = LocalDateTime.now().dayOfMonth
        val month = LocalDateTime.now().month.value
        val year = LocalDateTime.now().year

        var simpleQuery = ""
        when(filter){
            TodoSortType.ALL_TASKS->{
                simpleQuery =
                    "select * from todotable " +
                            "where dateDay =$date " +
                            "and dateMonth =$month " +
                            "and dateYear =$year"
            }
            TodoSortType.COMPLETED_TASKS->{
                simpleQuery =
                    "select * from todotable " +
                            "where dateDay =$date " +
                            "and completed =1 " +
                            "and dateMonth =$month " +
                            "and dateYear =$year"
            }
            TodoSortType.ACTIVE_TASKS->{
                simpleQuery =
                    "select * from todotable " +
                            "where dateDay =$date " +
                            "and completed =0 " +
                            "and dateMonth =$month " +
                            "and dateYear =$year"
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}