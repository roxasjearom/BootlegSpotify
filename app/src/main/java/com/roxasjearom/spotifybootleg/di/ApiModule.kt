package com.roxasjearom.spotifybootleg.di

import com.roxasjearom.spotifybootleg.data.remote.SpotifyAccountsService
import com.roxasjearom.spotifybootleg.data.remote.SpotifyApiService
import com.roxasjearom.spotifybootleg.data.remote.network.NetworkResultCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    private const val CLIENT_SECRET = "ADD YOUR CLIENT SECRET HERE"

    @Singleton
    @Provides
    fun provideSpotifyApi(): SpotifyApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()
            )
            .build()
            .create(SpotifyApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccountsApi(): SpotifyAccountsService {
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
            .create(SpotifyAccountsService::class.java)
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
