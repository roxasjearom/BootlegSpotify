package com.roxasjearom.spotifybootleg.data.repository

import com.roxasjearom.spotifybootleg.data.mapper.toCategory
import com.roxasjearom.spotifybootleg.data.remote.SpotifyRemoteDataSource
import com.roxasjearom.spotifybootleg.domain.model.Category
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: SpotifyRemoteDataSource,
) : MainRepository {
    override suspend fun getCategories(): List<Category> {
        return remoteDataSource.getCategories().categories.items.map { it.toCategory() }
    }
}
