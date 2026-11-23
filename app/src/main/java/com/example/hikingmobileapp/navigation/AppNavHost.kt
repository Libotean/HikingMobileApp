package com.example.hikingmobileapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.ui.screens.TrailDetailScreen
import com.example.hikingmobileapp.ui.screens.TrailListScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TrailList,
        modifier = modifier
    ) {
        composable<TrailList> {
            TrailListScreen(
                onTrailClick = { id -> navController.navigate(TrailDetail(id)) }
            )
        }

        composable<TrailDetail> { backStackEntry ->
            val detail: TrailDetail = backStackEntry.toRoute()
            val trail = TrailRepository.getById(detail.id)
            if (trail != null) {
                // shortcut: butonul nu salveaza inca nimic, se leaga de Room prin ViewModel
                TrailDetailScreen(
                    trail = trail,
                    isFavorite = false,
                    onToggleFavorite = {}
                )
            }
        }
    }
}
