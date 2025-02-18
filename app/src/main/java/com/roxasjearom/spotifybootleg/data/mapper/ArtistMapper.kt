package com.roxasjearom.spotifybootleg.data.mapper

import com.roxasjearom.spotifybootleg.data.remote.response.ArtistDto
import com.roxasjearom.spotifybootleg.domain.model.Artist

fun ArtistDto.toArtist() = Artist(
    id = id,
    name = name,
    imageUrl = images?.firstOrNull()?.url,
)
