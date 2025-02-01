package com.roxasjearom.spotifybootleg.data.repository

import com.roxasjearom.spotifybootleg.data.mapper.toCategory
import com.roxasjearom.spotifybootleg.data.remote.SpotifyRemoteDataSource
import com.roxasjearom.spotifybootleg.data.remote.network.Success
import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.AlbumDetails
import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Category
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: SpotifyRemoteDataSource,
) : MainRepository {
    override suspend fun getCategories(): List<Category> {
        val result = remoteDataSource.getCategories()

        return if (result is Success) {
            result.data.categories.items.map { it.toCategory() }
        } else {
            emptyList()
        }
    }

    override suspend fun getAlbums(): List<Album> {
        TODO("Not yet implemented")
    }

    override suspend fun getArtists(): List<Artist> {
        TODO("Not yet implemented")
    }

    override suspend fun getAlbumDetails(): AlbumDetails {
        TODO("Not yet implemented")
    }

    override suspend fun getAccessToken(): String {
        return remoteDataSource.getAccessToken().accessToken
    }
}
