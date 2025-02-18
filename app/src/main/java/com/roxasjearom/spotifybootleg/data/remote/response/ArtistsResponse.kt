package com.roxasjearom.spotifybootleg.data.remote.response

data class ArtistsResponse(
    val artists: List<ArtistDto>,
)

data class ArtistDto(
    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String,
    val followers: Followers?,
    val genres: List<String>?,
    val images: List<Image>?,
    val popularity: Long?,

)

data class Followers(
    val total: Long,
)

data class Image(
    val url: String,
    val height: Long,
    val width: Long,
)
