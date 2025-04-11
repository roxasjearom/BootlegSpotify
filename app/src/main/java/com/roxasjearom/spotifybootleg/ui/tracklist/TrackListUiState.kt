package com.roxasjearom.spotifybootleg.ui.tracklist

import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Track

data class TrackListUiState(
    val headerTitle: String = "-",
    val imageUrl: String? = null,
    val artists: List<Artist> = emptyList(),
    val tracks: List<Track> = emptyList(),
)
