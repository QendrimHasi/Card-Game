package com.example.cardgame.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cardgame.data.db.entities.Level
import com.example.cardgame.data.db.entities.Photo

@Database(entities = [Level::class,Photo::class],version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getLevelDao():LevelDao
    abstract fun getPhotoDao():PhotoDao

    companion object{
        @Volatile
        private var instance : AppDatabase?=null
        private  val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }
        private  fun buildDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db"
            ).build()
    }

}