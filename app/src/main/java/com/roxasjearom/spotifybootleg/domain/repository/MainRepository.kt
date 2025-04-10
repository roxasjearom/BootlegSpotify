package com.roxasjearom.spotifybootleg.domain.repository

import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.AlbumDetails
import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Category

interface MainRepository {
    suspend fun getCategories(): List<Category>

    suspend fun getAlbums(ids: List<String>): List<Album>

    suspend fun getArtists(ids: List<String>): List<Artist>

    suspend fun getAlbumDetails(id: String): AlbumDetails
}
