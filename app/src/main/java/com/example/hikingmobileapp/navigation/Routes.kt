package com.example.hikingmobileapp.navigation

import kotlinx.serialization.Serializable

@Serializable
object TrailList

@Serializable
data class TrailDetail(val id: Int)

@Serializable
object Favorites
