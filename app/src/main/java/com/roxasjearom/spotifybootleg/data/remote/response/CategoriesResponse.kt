package com.roxasjearom.spotifybootleg.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesResponse(
    val categories: Categories
)

data class Categories(
    val href: String,
    val items: List<CategoryDto>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val total: Int,
)

data class Icon(
    val height: Int,
    val url: String,
    val width: Int
)

data class CategoryDto(
    val href: String,
    val icons: List<Icon>,
    val id: String,
    val name: String
)
