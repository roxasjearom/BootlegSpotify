package com.roxasjearom.spotifybootleg.domain.model

data class AlbumDetails(
    val imageUrl: String,
    val name: String,
    val releaseDate: String,
    val artists: List<Artist>,
    val songs: List<Song>,
)
