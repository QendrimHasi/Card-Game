package com.example.cardgame.data.db.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo (
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val imageid : Int,
    val id : String = "",
    val owner : String? = null,
    val secret :String? = null,
    val server :String? = null,
    val farm : Int? = null,
    val title : String?=null,
    val ispublic : Int? = null,
    val isfriend : Int? = null,
    val isfamily : Int? = null,
    var clickt : Boolean = false,
    var matched : Boolean = false



)