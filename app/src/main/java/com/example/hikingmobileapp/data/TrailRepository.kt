package com.example.hikingmobileapp.data

import com.example.hikingmobileapp.model.Trail

object TrailREpository {
    private val trails = listOf(
        Trail(1, "Varful Ignis", 8.0, "Banda rosie", "Ignis", "Descriere traseu..."),
        Trail(2, "Creasta Cocosului", 6.0, "Punct albastru", "Gutai", "Descriere traseu..."),
        Trail(3, "Varful Gutai", 8.0, "Cruce galbena", "Gutai", "Descriere traseu..."),
        Trail(4, "Babele - Sfinxul - Varful Omu", 6.0, "Banda galbena", "Bucegi", "Descriere traseu..."),
        Trail(5, "Pietrele Doamnei", 5.0, "Cruce albastra", "Rarau", "Descriere traseu..."),
        Trail(6, "Cascada Cailor", 4.0, "Triunghi rosu", "Rodnei", "Descriere traseu..."),

        )

    fun getAll(): List<Trail> = trails

    fun getById(id: Int): Trail? = trails.find {it.id == id}
}