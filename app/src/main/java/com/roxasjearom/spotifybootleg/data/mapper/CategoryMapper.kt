package com.roxasjearom.spotifybootleg.data.mapper

import com.roxasjearom.spotifybootleg.data.remote.response.CategoryDto
import com.roxasjearom.spotifybootleg.domain.model.Category

fun CategoryDto.toCategory() = Category(
    id = id,
    name = name,
    iconUrl = icons.firstOrNull()?.url ?: "",
)
