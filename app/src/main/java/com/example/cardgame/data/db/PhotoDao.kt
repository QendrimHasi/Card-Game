package com.example.cardgame.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhotos(photos : List<Photo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSinglePhoto(photo : Photo)

    @Query("SELECT * FROM Photo")
    fun getAllPotos(): LiveData<List<Photo>>

    @Query("DELETE FROM Photo")
    fun deleteAllPhotos()

    @Query("SELECT * FROM Photo WHERE matched = :flag ")
    fun getListOfPhoto(flag:Int): LiveData<List<Photo>>

    @Query("UPDATE Photo SET matched = :flag , clickt = :flag")
    fun retartgame(flag : Boolean)
}