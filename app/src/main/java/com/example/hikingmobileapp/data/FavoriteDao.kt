package com.example.hikingmobileapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite_trails")
    fun getAll(): Flow<List<FavoriteTrail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favorite: FavoriteTrail)

    @Delete
    suspend fun delete(favorite: FavoriteTrail)
}
