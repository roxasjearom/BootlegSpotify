package com.roxasjearom.spotifybootleg.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.roxasjearom.spotifybootleg.ui.home.category.CategorySection

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeScreenUiState,
    onCategoryClicked: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        CategorySection(
            categories = uiState.categories,
            onCategoryClicked = onCategoryClicked,
        )
    }
}
