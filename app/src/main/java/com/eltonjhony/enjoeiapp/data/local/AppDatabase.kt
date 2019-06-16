package com.eltonjhony.enjoeiapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eltonjhony.enjoeiapp.data.local.entities.PhotoEntity
import com.eltonjhony.enjoeiapp.data.local.entities.ProductEntity
import com.eltonjhony.enjoeiapp.data.local.entities.UserEntity

@Database(entities = [ProductEntity::class, UserEntity::class, PhotoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao

    companion object {

        fun getDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, "app.db"
            ).build()
        }
    }
}