package com.roxasjearom.spotifybootleg.ui.songlist

import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Song

data class SongListUiState(
    val headerTitle: String = "-",
    val imageUrl: String? = null,
    val artists: List<Artist> = emptyList(),
    val songs: List<Song> = emptyList(),
)
