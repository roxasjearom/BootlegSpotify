package com.roxasjearom.spotifybootleg.domain.model

data class Track(
    val id: String,
    val name: String,
    val isExplicit: Boolean,
    val artists: List<Artist>,
)
