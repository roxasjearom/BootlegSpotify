package com.roxasjearom.spotifybootleg.di

import android.content.Context
import com.roxasjearom.spotifybootleg.data.remote.AuthenticationService
import com.roxasjearom.spotifybootleg.data.remote.SpotifyApiService
import com.roxasjearom.spotifybootleg.data.remote.authentication.AuthAuthenticator
import com.roxasjearom.spotifybootleg.data.remote.authentication.TokenManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://api.spotify.com/"
    private const val ACCOUNTS_URL = "https://accounts.spotify.com"
    private const val CLIENT_ID = "ADD YOUR CLIENT ID HERE"
    private const val CLIENT_SECRET = "ADD YOUR CLIENT ID HERE"

    @Singleton
    @Provides
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager = TokenManager(context)

    @Singleton
    @Provides
    fun provideSpotifyApi(authAuthenticator: AuthAuthenticator): SpotifyApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(
                OkHttpClient.Builder()
                    .authenticator(authAuthenticator)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(SpotifyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccountsApi(): AuthenticationService {
        return Retrofit.Builder()
            .baseUrl(ACCOUNTS_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(getAuthorizationClient().build())
            .build()
            .create(AuthenticationService::class.java)
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun getAuthorizationClient() = OkHttpClient().newBuilder()
        .addInterceptor(
            Interceptor { chain ->
                val base64Credentials =
                    Base64.Default.encode("$CLIENT_ID:$CLIENT_SECRET".toByteArray())
                val request: Request = chain.request()
                    .newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", "Basic $base64Credentials")
                    .build()
                chain.proceed(request)
            }
        )
}
