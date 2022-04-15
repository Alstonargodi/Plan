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
    @Query("select*from tabeloutside ORDER BY deadlinedate")
    abstract fun readoutside() : LiveData<List<outsideandsubtask>>


    //select subtask by id
    @Transaction
    @Query("select*from subtaskinside where idsub like :name")
    abstract fun selectsubtask(name : String): LiveData<List<subtaskinside>>


    @Delete
    abstract fun deletedata(todo: Inside)


    @Query("delete from tabelinside where title = :find")
    abstract fun deletefindinside(find:String)



    @Update
    abstract fun updatedata(todo: Inside)

    @Query("delete from tabelInside")
    abstract fun destroytodo()

}