package com.example.hikingmobileapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hikingmobileapp.R
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.model.Trail
import com.example.hikingmobileapp.ui.components.TrailCard
import com.example.hikingmobileapp.ui.theme.HikingMobileAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    trails: List<Trail>,
    onTrailClick: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(stringResource(R.string.favorites_title)) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            // Scaffold-ul din MainActivity lasa deja loc pentru bara de stare
            windowInsets = WindowInsets(0)
        )

        if (trails.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(stringResource(R.string.no_favorites))
            }
        } else {
            LazyColumn {
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
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    HikingMobileAppTheme {
        FavoritesScreen(
            trails = TrailRepository.getAll().take(3),
            onTrailClick = {},
            onRemove = {},
            onBack = {}
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
            onRemove = {},
            onBack = {}
        )
    }
}
