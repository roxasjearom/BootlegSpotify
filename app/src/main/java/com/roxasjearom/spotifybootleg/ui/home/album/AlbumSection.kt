package com.roxasjearom.spotifybootleg.ui.home.album

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.spotifybootleg.R
import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme

@Composable
fun AlbumSection(
    modifier: Modifier = Modifier,
    albums: List<Album>,
    onAlbumClicked: (String) -> Unit,
    onShowAllAlbumsClicked: () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.header_album),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                onClick = onShowAllAlbumsClicked,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.label_show_all),
                )
            }
        }

        LazyRow {
            items(albums) { album ->
                AlbumItem(album = album, onAlbumClicked = onAlbumClicked)
            }
        }
    }
}

@Composable
fun AlbumItem(
    modifier: Modifier = Modifier,
    album: Album,
    onAlbumClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .width(124.dp)
            .wrapContentHeight()
            .clickable { onAlbumClicked(album.id) }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(album.imageUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(124.dp)
                .height(124.dp)
        )
        Text(
            text = album.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(CenterHorizontally),
        )
    }
}

@Preview
@Composable
fun AlbumPreview(modifier: Modifier = Modifier) {
    SpotifyBootlegTheme {
        AlbumItem(
            album = Album(
                id = "382ObEPsp2rxGrnsizN5TX",
                name = "TRON: Legacy Reconfigured",
                imageUrl = "https://i.scdn.co/image/ab67616d00001e0226597c053b38c9cf93f8f3a9",
            ), onAlbumClicked = {}
        )
    }
}
