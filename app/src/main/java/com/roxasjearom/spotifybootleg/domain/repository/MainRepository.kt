package com.roxasjearom.spotifybootleg.domain.repository

import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.Category

interface MainRepository {
    suspend fun getAccessToken(): String

    suspend fun getCategories(): List<Category>

    suspend fun getAlbums(): List<Album>
}
