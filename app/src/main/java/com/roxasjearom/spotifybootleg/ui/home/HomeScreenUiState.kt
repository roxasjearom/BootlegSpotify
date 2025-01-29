package com.roxasjearom.spotifybootleg.ui.home

import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Category

data class HomeScreenUiState(
    val categories: List<Category> = emptyList(),
    val albums: List<Album> = emptyList(),
    val artists: List<Artist> = emptyList(),
)
