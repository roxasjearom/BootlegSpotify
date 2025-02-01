package com.roxasjearom.spotifybootleg.ui.songlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Song
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme

@Composable
fun SongListScreen(
    title: String,
    subtitle: String,
    imageUrl: String,
    songs: List<Song> = emptyList(),
    modifier: Modifier = Modifier,
    maxImageSize: Dp = 300.dp,
    minImageSize: Dp = 100.dp
) {
    var currentImageSize by remember { mutableStateOf(maxImageSize) }
    var imageScale by remember { mutableFloatStateOf(1f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newImageSize = currentImageSize + delta.dp
                val previousImageSize = currentImageSize

                currentImageSize = newImageSize.coerceIn(minImageSize, maxImageSize)
                val consumed = currentImageSize - previousImageSize

                imageScale = currentImageSize / maxImageSize

                return Offset(0f, consumed.value)
            }
        }
    }

    Box(modifier = modifier.nestedScroll(nestedScrollConnection)) {
        Column(modifier = Modifier.offset {
            IntOffset(0, currentImageSize.roundToPx())
        }) {
            HeaderSection(
                title = title,
                subtitle = subtitle,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp)

            ) {
                items(songs) { song ->
                    SongItem(song = song)
                }
            }
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(maxImageSize)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    scaleX = imageScale
                    scaleY = imageScale
                    translationY = -(maxImageSize.toPx() - currentImageSize.toPx()) / 2f
                }
        )
    }
}

@Composable
fun HeaderSection(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
fun SongItem(song: Song, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(all = 8.dp)
    ) {
        val artists = song.artists.joinToString(", ") { it.name }

        Text(
            text = song.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (song.isExplicit) {
                ExplicitTag()
                Spacer(modifier = Modifier.width(4.dp))
            }

            Text(
                text = artists,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Composable
fun ExplicitTag() {
    Text(
        text = "E",
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .clip(RoundedCornerShape(2.dp))
            .background(Color.LightGray)
            .padding(horizontal = 4.dp)
    )
}

@Preview(showBackground = true, heightDp = 600)
@Composable
fun SongListScreenPreview() {
    SpotifyBootlegTheme {
        SongListScreen(
            title = "Global Warming",
            subtitle = "Pitbull",
            imageUrl = "https://i.scdn.co/image/ab67616d0000b2732c5b24ecfa39523a75c993c4",
            songs = listOf(
                Song(
                    id = "292kifgxa7S78AuzA5NMpL",
                    name = "Global Warming (feat. Sensato)",
                    artists = listOf(
                        Artist(
                            id = "0TnOYISbd1XYRBk9myaseg",
                            name = "Pitbull",
                            imageUrl = null,
                        )
                    ),
                    isExplicit = true,
                ),
                Song(
                    id = "0Hf4aIJpsN4Os2f0y0VqWl",
                    name = "Feel This Moment (feat. Christina Aguilera)",
                    artists = listOf(
                        Artist(
                            id = "0TnOYISbd1XYRBk9myaseg",
                            name = "Pitbull",
                            imageUrl = null,
                        )
                    ),
                    isExplicit = false,
                ),
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SongItemPreview() {
    SpotifyBootlegTheme {
        SongItem(
            song = Song(
                id = "292kifgxa7S78AuzA5NMpL",
                name = "Global Warming (feat. Sensato)",
                artists = listOf(
                    Artist(
                        id = "0TnOYISbd1XYRBk9myaseg",
                        name = "Pitbull",
                        imageUrl = null
                    )
                ),
                isExplicit = true,
            ),
        )
    }
}
