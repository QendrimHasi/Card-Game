package com.example.cardgame.data.network.responses

import com.example.cardgame.data.db.entities.Photo

data class Photos(
    val page : Int?=null,
    val pages : Int?=null,
    val perpage : Int?=null,
    val total : String?=null,
    val photo : ArrayList<Photo>

)