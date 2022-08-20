package com.example.wetterbericht.helpers.sortfilter

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
        val currentDate = LocalDateTime.now().dayOfMonth
        var simpleQuery = ""

//      select * from todotable where dateDay =:20 and completed =1
        when(filter){
            TodoSortType.ALL_TASKS->{
                simpleQuery =
                    "select * from todotable where dateDay =$currentDate"
            }
            TodoSortType.COMPLETED_TASKS->{
                simpleQuery =
                    "select * from todotable where dateDay =$currentDate and completed =1"
            }
            TodoSortType.ACTIVE_TASKS->{
                simpleQuery =
                    "select * from todotable where dateDay =$currentDate and completed =0"
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}