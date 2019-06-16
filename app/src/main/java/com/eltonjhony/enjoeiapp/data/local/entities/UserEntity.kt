package com.eltonjhony.enjoeiapp.data.local.entities
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey @ColumnInfo(name = "user_id") val id: Long,
    val name: String,
    @Embedded val avatar: PhotoEntity
)