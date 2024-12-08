package com.roxasjearom.spotifybootleg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.roxasjearom.spotifybootleg.ui.home.HomeScreen
import com.roxasjearom.spotifybootleg.ui.home.HomeScreenUiState
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyBootlegTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = HomeScreenUiState(),
                        onCategoryClicked = {},
                    )
                }
            }
        }
    }
}
