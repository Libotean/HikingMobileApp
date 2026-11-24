package com.example.hikingmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.hikingmobileapp.R
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.ui.components.TrailCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailListScreen(
    onTrailClick: (Int) -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val trails = TrailRepository.getAll()

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(stringResource(R.string.trails_title)) },
            actions = {
                IconButton(onClick = onFavoritesClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_favorite),
                        contentDescription = stringResource(R.string.favorites_title)
                    )
                }
            },
            // Scaffold-ul din MainActivity lasa deja loc pentru bara de stare
            windowInsets = WindowInsets(0)
        )

        LazyColumn {
            items(trails) { trail ->
                TrailCard(trail = trail, onClick = {onTrailClick(trail.id)})
            }
        }
    }
}
