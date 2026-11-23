package com.example.hikingmobileapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_trails")
data class FavoriteTrail(
    @PrimaryKey val trailId: Int
)
