package com.example.hikingmobileapp.model

data class Trail(
    val id: Int,
    val name: String,
    val lengthKm: Double,
    val marking: String,
    val zone: String,
    val description: String
)