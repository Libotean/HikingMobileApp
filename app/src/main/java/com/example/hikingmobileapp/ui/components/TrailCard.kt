package com.example.hikingmobileapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.model.Trail
import com.example.hikingmobileapp.ui.theme.HikingMobileAppTheme
import androidx.compose.foundation.layout.width
import androidx.compose.ui.text.font.FontWeight

@Composable
fun TrailCard(
    trail: Trail,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = trail.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = "${trail.lengthKm} km")
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = trail.marking)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrailCardPreview() {
    HikingMobileAppTheme {
        Column {
            TrailRepository.getAll().take(3).forEach { trail ->
                TrailCard(
                    trail = trail,
                    onClick = {}
                )
            }
        }
    }
}