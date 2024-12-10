package com.roxasjearom.spotifybootleg.domain.repository

import com.roxasjearom.spotifybootleg.domain.model.Category

interface MainRepository {
    suspend fun getCategories(): List<Category>
}
