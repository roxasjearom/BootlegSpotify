package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.network.NetworkResult
import com.roxasjearom.spotifybootleg.data.remote.response.AccessTokenResponse
import com.roxasjearom.spotifybootleg.data.remote.response.CategoriesResponse
import javax.inject.Inject

class SpotifyRemoteDataSource @Inject constructor(
    private val apiService: SpotifyApiService,
    private val accountsService: SpotifyAccountsService,
    ) {

    suspend fun getCategories(): NetworkResult<CategoriesResponse> {
        return apiService.getCategories()
    }
    suspend fun getAccessToken(): AccessTokenResponse {
        return accountsService.getAccessToken()
    }

}
