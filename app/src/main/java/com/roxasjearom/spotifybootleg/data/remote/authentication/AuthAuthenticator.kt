package com.roxasjearom.spotifybootleg.data.remote.authentication

import com.roxasjearom.spotifybootleg.data.remote.AuthenticationService
import com.roxasjearom.spotifybootleg.data.remote.response.AccessTokenResponse
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AuthAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authenticationService: AuthenticationService,
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val newToken = getAccessToken()
            if (newToken == null) { //Couldn't get the token, so restart the process
                tokenManager.deleteAccessToken()
            }

            newToken?.let { accessToken ->
                tokenManager.saveAccessToken(accessToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer $accessToken")
                    .build()
            }
        }
    }

    private suspend fun getAccessToken(): String? {
        val accessToken = runBlocking {
            tokenManager.getAccessToken().first()
        }
        return accessToken ?: getNewAccessToken().body()?.accessToken
    }

    private suspend fun getNewAccessToken(): retrofit2.Response<AccessTokenResponse> {
        return authenticationService.getAccessToken()
    }
}
