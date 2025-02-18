package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.response.AlbumDto
import com.roxasjearom.spotifybootleg.data.remote.response.AlbumResponse
import com.roxasjearom.spotifybootleg.data.remote.response.ArtistsResponse
import com.roxasjearom.spotifybootleg.data.remote.response.CategoriesResponse
import javax.inject.Inject

class SpotifyRemoteDataSource @Inject constructor(
    private val apiService: SpotifyApiService,
) {
    suspend fun getCategories(): CategoriesResponse {
        return apiService.getCategories()
    }

    suspend fun getArtists(ids: List<String>): ArtistsResponse {
        return apiService.getArtists(ids.joinToString(","))
    }

    suspend fun getAlbums(ids: List<String>): AlbumResponse {
        return apiService.getAlbums(ids.joinToString(","))
    }

    suspend fun getAlbumDetails(id: String): AlbumDto {
        return apiService.getAlbumDetails(id)
    }
}
