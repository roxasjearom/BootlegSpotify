package com.roxasjearom.spotifybootleg.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class TopLevelRoute<T : Any>(
    val name: String,
    val route: T,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
)

@Serializable
sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data class TrackList(val id: String) : Route()

    @Serializable
    data object Search : Route()

    @Serializable
    data object Library : Route()
}
