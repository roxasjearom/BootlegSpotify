package com.roxasjearom.spotifybootleg.data.remote

import com.roxasjearom.spotifybootleg.data.remote.response.AccessTokenResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationService {

    @FormUrlEncoded
    @POST("api/token")
    suspend fun getAccessToken(
        @Field("grant_type") grantType: String = "client_credentials",
    ): Response<AccessTokenResponse>
}
