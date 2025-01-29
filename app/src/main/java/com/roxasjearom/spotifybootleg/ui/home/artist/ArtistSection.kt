package com.roxasjearom.spotifybootleg.ui.home.artist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.spotifybootleg.R
import com.roxasjearom.spotifybootleg.domain.model.Artist

@Composable
fun ArtistSection(
    modifier: Modifier = Modifier,
    artists: List<Artist>,
    onArtistClicked: (String) -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.header_artist),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(8.dp)
            )
        }

        LazyRow {
            items(artists) { artist ->
                ArtistItem(artist = artist, onArtistClicked = onArtistClicked)
            }
        }
    }
}

@Composable
fun ArtistItem(
    modifier: Modifier = Modifier,
    artist: Artist,
    onArtistClicked: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .width(124.dp)
            .wrapContentHeight()
            .clickable { onArtistClicked(artist.id) }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(artist.imageUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .width(124.dp)
                .height(124.dp)
        )
        Text(
            text = artist.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.align(CenterHorizontally),
        )
    }
}
