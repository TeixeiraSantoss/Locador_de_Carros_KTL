package com.app.condominioapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Moradores")
data class Morador (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    var docId : String,
    val marca : String,
    val modelo : String,
    val ano : Int,
    var alugado : Boolean
) {
    constructor() : this(0,"", "", "", 0, false)
}

