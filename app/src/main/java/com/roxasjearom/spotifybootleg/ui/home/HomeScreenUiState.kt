package com.roxasjearom.spotifybootleg.ui.home

import com.roxasjearom.spotifybootleg.domain.model.Category

data class HomeScreenUiState(
    val categories: List<Category> = emptyList(),
)
