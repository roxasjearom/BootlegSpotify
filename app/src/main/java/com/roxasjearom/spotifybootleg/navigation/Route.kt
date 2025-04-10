package com.roxasjearom.spotifybootleg.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Home: Route()

    @Serializable
    data class TrackList(val id: String): Route()
}
