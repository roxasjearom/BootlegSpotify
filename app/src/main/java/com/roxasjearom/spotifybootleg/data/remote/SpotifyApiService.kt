package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.response.CategoriesResponse
import retrofit2.http.GET

interface SpotifyApiService {
    companion object {
        private const val API_VERSION = "v1"
    }

    @GET("${API_VERSION}/browse/categories")
    suspend fun getCategories(): CategoriesResponse
}
