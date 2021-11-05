package com.example.wetterbericht.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wetterbericht.model.room.*
import com.example.wetterbericht.model.room.Do.Outside
import com.example.wetterbericht.model.room.Do.outsideandsubtask
import com.example.wetterbericht.model.room.Do.subtaskoutside

@Dao
abstract class todoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun adddatainside(todo: Inside)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addoutside(outside : Outside)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addsubinside(subtask: ArrayList<subtaskinside>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addsuboutside(subtask: ArrayList<subtaskoutside>)


    @Transaction
    @Query("select*from tabelinside")
    abstract fun readinside() : LiveData<List<insidendsubtask>>

    @Transaction
    @Query("select*from tabeloutside")
    abstract fun readoutside() : LiveData<List<outsideandsubtask>>


    @Delete
    abstract fun deletedata(todo: Inside)

    @Update
    abstract fun updatedata(todo: Inside)

    @Query("delete from tabelInside")
    abstract fun destroytodo()

}