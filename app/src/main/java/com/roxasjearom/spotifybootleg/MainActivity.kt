package com.roxasjearom.spotifybootleg

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.roxasjearom.spotifybootleg.navigation.Route
import com.roxasjearom.spotifybootleg.navigation.TopLevelRoute
import com.roxasjearom.spotifybootleg.ui.home.HomeScreen
import com.roxasjearom.spotifybootleg.ui.home.HomeScreenViewModel
import com.roxasjearom.spotifybootleg.ui.library.LibraryScreen
import com.roxasjearom.spotifybootleg.ui.search.SearchScreen
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme
import com.roxasjearom.spotifybootleg.ui.tracklist.TrackListScreen
import com.roxasjearom.spotifybootleg.ui.tracklist.TrackListViewModel
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

                val topLevelRoutes = listOf(
                    TopLevelRoute("Home", Route.Home, Icons.Default.Home),
                    TopLevelRoute("Search", Route.Search, Icons.Default.Search),
                    TopLevelRoute("Library", Route.Library, Icons.Default.Menu),
                )
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                Scaffold(
                    bottomBar = {
                        NavigationBar {
                            topLevelRoutes.forEach { topLevelRoute ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            topLevelRoute.icon,
                                            contentDescription = topLevelRoute.name
                                        )
                                    },
                                    label = { Text(topLevelRoute.name) },
                                    selected = currentRoute.equals(topLevelRoute.name, true),
                                    onClick = {
                                        navController.navigate(topLevelRoute.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
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
                                    Toast.makeText(
                                        context,
                                        "Navigate to Song list screen. Category Id: $it",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                },
                                onAlbumClicked = { id ->
                                    navController.navigate(Route.TrackList(id))
                                },
                                onArtistClicked = {
                                    Toast.makeText(
                                        context,
                                        "Navigate to Song list screen. Artist Id: $it",
                                        Toast.LENGTH_SHORT
                                    ).show()
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
                                onBackClick = { navController.popBackStack() },
                            )
                        }
                        composable<Route.Search> { SearchScreen() }
                        composable<Route.Library> { LibraryScreen() }
                    }
                }
            }
        }
    }
}
