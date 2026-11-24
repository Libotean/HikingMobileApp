package com.example.hikingmobileapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.hikingmobileapp.data.FavoriteDao
import com.example.hikingmobileapp.data.FavoriteTrail
import com.example.hikingmobileapp.data.HikingDatabase
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.model.Trail
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(private val dao: FavoriteDao) : ViewModel() {

    // Room da un Flow de id-uri; il transformam in trasee si il tinem ca StateFlow
    val favorites: StateFlow<List<Trail>> =
        dao.getAll()
            .map { list -> list.mapNotNull { TrailRepository.getById(it.trailId) } }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )

    fun onToggleFavorite(id: Int) {
        viewModelScope.launch {
            if (favorites.value.any { it.id == id }) dao.delete(FavoriteTrail(id))
            else dao.insert(FavoriteTrail(id))
        }
    }

    fun onRemove(id: Int) {
        viewModelScope.launch { dao.delete(FavoriteTrail(id)) }
    }

    companion object {
        // viewModel() nu stie singur sa-i dea DAO-ul, asa ca ii aratam noi cum
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as Application
                FavoritesViewModel(HikingDatabase.getDatabase(app).favoriteDao())
            }
        }
    }
}
