package com.eltonjhony.enjoeiapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey @ColumnInfo(name = "public_id") val publicId: String,
    val crop: String,
    val gravity: String)