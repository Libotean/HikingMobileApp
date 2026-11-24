package com.example.hikingmobileapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hikingmobileapp.R
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.model.Trail
import com.example.hikingmobileapp.ui.components.TrailCard
import com.example.hikingmobileapp.ui.theme.HikingMobileAppTheme

@Composable
fun FavoritesScreen(
    trails: List<Trail>,
    onTrailClick: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (trails.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.no_favorites))
        }
    } else {
        LazyColumn(modifier = modifier) {
            items(trails, key = { it.id }) { trail ->
                Column {
                    TrailCard(trail = trail, onClick = { onTrailClick(trail.id) })
                    TextButton(
                        onClick = { onRemove(trail.id) },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(stringResource(R.string.remove_favorite))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    HikingMobileAppTheme {
        FavoritesScreen(
            trails = TrailRepository.getAll().take(3),
            onTrailClick = {},
            onRemove = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenEmptyPreview() {
    HikingMobileAppTheme {
        FavoritesScreen(
            trails = emptyList(),
            onTrailClick = {},
            onRemove = {}
        )
    }
}
