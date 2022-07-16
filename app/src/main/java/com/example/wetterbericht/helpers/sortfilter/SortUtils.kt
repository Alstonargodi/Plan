package com.example.wetterbericht.helpers.sortfilter

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.wetterbericht.data.local.entity.todolist.TodoLocal
import java.lang.StringBuilder

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
        val simpleQuery = StringBuilder().append("SELECT * FROM TodoTable ")
        when(filter){
            TodoSortType.COMPLETED_TASKS->{
                simpleQuery.append("WHERE completed = 1")
            }
            TodoSortType.ACTIVE_TASKS->{
                simpleQuery.append("WHERE completed = 0")
            }
            else->{}
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}