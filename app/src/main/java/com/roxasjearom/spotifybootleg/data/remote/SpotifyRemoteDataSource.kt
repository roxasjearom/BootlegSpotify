package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.response.CategoriesResponse
import javax.inject.Inject

class SpotifyRemoteDataSource @Inject constructor(private val apiService: SpotifyApiService) {

    suspend fun getCategories(): CategoriesResponse {
        return apiService.getCategories()
    }

}
