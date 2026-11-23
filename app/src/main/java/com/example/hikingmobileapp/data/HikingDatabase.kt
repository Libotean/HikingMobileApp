package com.example.hikingmobileapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteTrail::class], version = 1, exportSchema = false)
abstract class HikingDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var instance: HikingDatabase? = null

        fun getDatabase(context: Context): HikingDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    HikingDatabase::class.java,
                    "hiking_database"
                ).build().also { instance = it }
            }
    }
}
