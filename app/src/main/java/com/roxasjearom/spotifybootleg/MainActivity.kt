package com.roxasjearom.spotifybootleg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.roxasjearom.spotifybootleg.ui.home.HomeScreen
import com.roxasjearom.spotifybootleg.ui.home.HomeScreenViewModel
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyBootlegTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel: HomeScreenViewModel by viewModels()
                    HomeScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = viewModel.homeUiState.collectAsStateWithLifecycle().value,
                        onCategoryClicked = {
                            Toast.makeText(this, "Navigate to Song list screen. Category Id: $it", Toast.LENGTH_SHORT).show()
                        },
                        onAlbumClicked = {
                            Toast.makeText(this, "Navigate to Song list screen. Album Id: $it", Toast.LENGTH_SHORT).show()
                        },
                        onArtistClicked = {
                            Toast.makeText(this, "Navigate to Song list screen. Artist Id: $it", Toast.LENGTH_SHORT).show()
                        },
                    )
                }
            }
        }
    }
}
