package com.roxasjearom.spotifybootleg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roxasjearom.spotifybootleg.navigation.Route
import com.roxasjearom.spotifybootleg.ui.home.HomeScreen
import com.roxasjearom.spotifybootleg.ui.home.HomeScreenViewModel
import com.roxasjearom.spotifybootleg.ui.tracklist.TrackListScreen
import com.roxasjearom.spotifybootleg.ui.tracklist.TrackListViewModel
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpotifyBootlegTheme {
                val navController = rememberNavController()
                val context = LocalContext.current

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Route.Home> {
                            val viewModel: HomeScreenViewModel = hiltViewModel()
                            HomeScreen(
                                modifier = Modifier.padding(innerPadding),
                                uiState = viewModel.homeUiState.collectAsStateWithLifecycle().value,
                                onCategoryClicked = {
                                    Toast.makeText(context, "Navigate to Song list screen. Category Id: $it", Toast.LENGTH_SHORT).show()
                                },
                                onAlbumClicked = { id ->
                                    navController.navigate(Route.TrackList(id))
                                },
                                onArtistClicked = {
                                    Toast.makeText(context, "Navigate to Song list screen. Artist Id: $it", Toast.LENGTH_SHORT).show()
                                },
                            )
                        }
                        composable<Route.TrackList> {
                            val viewModel: TrackListViewModel = hiltViewModel()
                            TrackListScreen(
                                title = viewModel.trackListUiState.collectAsStateWithLifecycle().value.headerTitle,
                                subtitle = viewModel.trackListUiState.collectAsStateWithLifecycle().value.artists.joinToString(
                                    ", "
                                ) { it.name },
                                imageUrl = viewModel.trackListUiState.collectAsStateWithLifecycle().value.imageUrl
                                    ?: "",
                                tracks = viewModel.trackListUiState.collectAsStateWithLifecycle().value.tracks,
                                modifier = Modifier.padding(innerPadding),
                            )
                        }
                    }
                }
            }
        }
    }
}
