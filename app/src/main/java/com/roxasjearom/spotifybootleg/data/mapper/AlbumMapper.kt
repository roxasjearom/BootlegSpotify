package com.roxasjearom.spotifybootleg.data.mapper

import com.roxasjearom.spotifybootleg.data.remote.response.AlbumDto
import com.roxasjearom.spotifybootleg.data.remote.response.Item
import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.AlbumDetails
import com.roxasjearom.spotifybootleg.domain.model.Song

fun AlbumDto.toAlbum() = Album(
    id = id,
    name = name,
    imageUrl = images.firstOrNull()?.url ?: "",
)

fun AlbumDto.toAlbumDetails() = AlbumDetails(
    imageUrl = images.firstOrNull()?.url ?: "",
    name = name,
    releaseDate = releaseDate,
    artists = artists.map { it.toArtist() },
    songs = tracks.items.map { it.toSongs() }
)

fun Item.toSongs() = Song(
    id = id,
    name = name,
    isExplicit = explicit,
    artists = artists.map { it.toArtist() },
)
