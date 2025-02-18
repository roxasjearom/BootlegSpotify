package com.roxasjearom.spotifybootleg.data.repository

import com.roxasjearom.spotifybootleg.data.mapper.toAlbum
import com.roxasjearom.spotifybootleg.data.mapper.toAlbumDetails
import com.roxasjearom.spotifybootleg.data.mapper.toArtist
import com.roxasjearom.spotifybootleg.data.mapper.toCategory
import com.roxasjearom.spotifybootleg.data.remote.SpotifyRemoteDataSource
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
        return remoteDataSource.getCategories().categories.items.map { it.toCategory() }
    }

    override suspend fun getAlbums(ids: List<String>): List<Album> {
        return remoteDataSource.getAlbums(ids).albums.map { it.toAlbum() }
    }

    override suspend fun getArtists(ids: List<String>): List<Artist> {
        return remoteDataSource.getArtists(ids).artists.map { it.toArtist() }
    }

    override suspend fun getAlbumDetails(id: String): AlbumDetails {
        return remoteDataSource.getAlbumDetails(id).toAlbumDetails()
    }
}
