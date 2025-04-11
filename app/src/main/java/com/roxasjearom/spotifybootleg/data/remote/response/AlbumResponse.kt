package com.roxasjearom.spotifybootleg.data.remote.response

import com.squareup.moshi.Json

data class AlbumResponse(
    val albums: List<AlbumDto>,
)

data class AlbumDto(
    @Json(name = "album_type")
    val albumType: String,
    @Json(name = "total_tracks")
    val totalTracks: Long,
    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "release_date_precision")
    val releaseDatePrecision: String,
    val type: String,
    val uri: String,
    val artists: List<ArtistDto>,
    val tracks: TracksDto,
    val copyrights: List<Copyright>,
    val label: String,
    val popularity: Long,
)

data class TracksDto(
    val href: String,
    val limit: Long,
    val next: Any?,
    val offset: Long,
    val previous: Any?,
    val total: Long,
    val items: List<ItemDto>,
)

data class ItemDto(
    val artists: List<ArtistDto>,
    @Json(name = "disc_number")
    val discNumber: Long,
    @Json(name = "duration_ms")
    val durationMs: Long,
    val explicit: Boolean,
    val href: String,
    val id: String,
    val name: String,
    @Json(name = "preview_url")
    val previewUrl: String?,
    @Json(name = "track_number")
    val trackNumber: Long,
    val type: String,
    val uri: String,
    @Json(name = "is_local")
    val isLocal: Boolean,
)

data class Copyright(
    val text: String,
    val type: String,
)
