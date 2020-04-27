package com.example.cardgame.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cardgame.data.db.entities.Level

@Dao
interface LevelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSaveUpdateLevel(level : Level)

    @Query("SELECT * FROM Level WHERE id= :id")
    fun getLevelById(id : Int): LiveData<Level>

    @Query("SELECT * FROM Level WHERE id= :id")
    fun getLevel(id : Int): Level

    @Query("SELECT * FROM Level")
    fun getAllLevels():LiveData<List<Level>>
}