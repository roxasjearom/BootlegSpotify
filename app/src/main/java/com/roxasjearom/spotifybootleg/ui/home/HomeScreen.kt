package com.roxasjearom.spotifybootleg.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.roxasjearom.spotifybootleg.ui.home.album.AlbumSection
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
            onShowAllClicked = {},
        )

        Spacer(modifier = Modifier.height(8.dp))

        AlbumSection(
            albums = uiState.albums,
            onAlbumClicked = {},
            onShowAllAlbumsClicked = {},
        )
    }
}
