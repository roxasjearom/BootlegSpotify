package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.response.AlbumDto
import com.roxasjearom.spotifybootleg.data.remote.response.AlbumResponse
import com.roxasjearom.spotifybootleg.data.remote.response.ArtistsResponse
import com.roxasjearom.spotifybootleg.data.remote.response.CategoriesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyApiService {
    companion object {
        private const val API_VERSION = "v1"
    }

    @GET("${API_VERSION}/browse/categories")
    suspend fun getCategories(@Query("limit") limit: Int = 10): CategoriesResponse

    @GET("${API_VERSION}/artists")
    suspend fun getArtists(@Query("ids") ids: String): ArtistsResponse

    @GET("${API_VERSION}/albums")
    suspend fun getAlbums(@Query("ids") ids: String): AlbumResponse

    @GET("${API_VERSION}/albums/{id}")
    suspend fun getAlbumDetails(@Path("id") id: String): AlbumDto
}
