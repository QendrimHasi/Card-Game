package com.example.cardgame.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Level (
    @PrimaryKey(autoGenerate = false)
    val id : Int?=null,
    var score : Int?=null,
    var finished : Boolean = false
)