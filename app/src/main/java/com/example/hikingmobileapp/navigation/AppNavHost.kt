package com.example.hikingmobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.ui.FavoritesViewModel
import com.example.hikingmobileapp.ui.screens.FavoritesScreen
import com.example.hikingmobileapp.ui.screens.TrailDetailScreen
import com.example.hikingmobileapp.ui.screens.TrailListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    // Creat aici, deasupra NavHost: detaliul si ecranul Favorite folosesc acelasi ViewModel
    val favoritesViewModel: FavoritesViewModel = viewModel(factory = FavoritesViewModel.Factory)
    val favorites by favoritesViewModel.favorites.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = TrailList,
        modifier = modifier
    ) {
        composable<TrailList> {
            TrailListScreen(
                onTrailClick = { id -> navController.navigate(TrailDetail(id)) },
                onFavoritesClick = { navController.navigate(Favorites) }
            )
        }

        composable<TrailDetail> { backStackEntry ->
            val detail: TrailDetail = backStackEntry.toRoute()
            val trail = TrailRepository.getById(detail.id)
            if (trail != null) {
                TrailDetailScreen(
                    trail = trail,
                    isFavorite = favorites.any { it.id == trail.id },
                    onToggleFavorite = { favoritesViewModel.onToggleFavorite(trail.id) },
                    onBack = { navController.popBackStack() }
                )
            }
        }

        composable<Favorites> {
            FavoritesScreen(
                trails = favorites,
                onTrailClick = { id -> navController.navigate(TrailDetail(id)) },
                onRemove = favoritesViewModel::onRemove,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
