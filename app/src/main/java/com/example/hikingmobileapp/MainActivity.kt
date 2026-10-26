package com.example.hikingmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.hikingmobileapp.data.TrailRepository
import com.example.hikingmobileapp.ui.screens.TrailDetailScreen
import com.example.hikingmobileapp.ui.screens.TrailListScreen
import com.example.hikingmobileapp.ui.theme.HikingMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HikingMobileAppTheme {
                var selectedTrailId by rememberSaveable { mutableStateOf<Int?>(null) }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val id = selectedTrailId

                    if (id == null) {
                        TrailListScreen(
                            onTrailClick = { selectedTrailId = it },
                            modifier = Modifier.padding(innerPadding)
                        )
                    } else {
                        BackHandler { selectedTrailId = null }

                        val trail = TrailRepository.getById(id)
                        if (trail != null) {
                            TrailDetailScreen(
                                trail = trail,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}