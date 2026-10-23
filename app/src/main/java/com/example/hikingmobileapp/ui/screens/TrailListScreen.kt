package com.example.hikingmobileapp.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.ui.components.TrailCard

@Composable
fun TrailListScreen(
    onTrailClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val trails = TrailRepository.getAll()

    LazyColumn(modifier = modifier) {
        items(trails) { trail ->
            TrailCard(trail = trail, onClick = {onTrailClick(trail.id)})
        }
    }
}