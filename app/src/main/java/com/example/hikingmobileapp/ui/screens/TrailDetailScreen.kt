package com.example.hikingmobileapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.hikingmobileapp.R
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.model.Trail
import com.example.hikingmobileapp.ui.theme.HikingMobileAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrailDetailScreen(
    trail: Trail,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text(stringResource(R.string.trail_details)) },
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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            Text(
                text = trail.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Muntii ${trail.zone}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Lungime: ${trail.lengthKm} km")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Marcaj: ${trail.marking}")

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = trail.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isFavorite) {
                OutlinedButton(onClick = onToggleFavorite) {
                    Text(stringResource(R.string.remove_favorite))
                }
            } else {
                Button(onClick = onToggleFavorite) {
                    Text(stringResource(R.string.add_favorite))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrailDetailScreenPreview() {
    HikingMobileAppTheme {
        TrailDetailScreen(
            trail = TrailRepository.getAll().first(),
            isFavorite = false,
            onToggleFavorite = {},
            onBack = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrailDetailScreenFavoritePreview() {
    HikingMobileAppTheme {
        TrailDetailScreen(
            trail = TrailRepository.getAll().first(),
            isFavorite = true,
            onToggleFavorite = {},
            onBack = {}
        )
    }
}
