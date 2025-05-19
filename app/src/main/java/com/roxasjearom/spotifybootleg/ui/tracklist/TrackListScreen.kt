package com.roxasjearom.spotifybootleg.ui.tracklist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.roxasjearom.spotifybootleg.domain.model.Artist
import com.roxasjearom.spotifybootleg.domain.model.Track
import com.roxasjearom.spotifybootleg.ui.theme.SpotifyBootlegTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackListScreen(
    title: String,
    subtitle: String,
    imageUrl: String,
    modifier: Modifier = Modifier,
    tracks: List<Track> = emptyList(),
    maxImageSize: Dp = 320.dp,
    minImageSize: Dp = 62.dp,
    onBackClicked: () -> Unit,
    onTrackClicked: (String) -> Unit,
) {
    var currentImageSize by remember { mutableStateOf(maxImageSize) }
    var imageScale by remember { mutableFloatStateOf(1f) }
    var currentImageAlpha by remember { mutableFloatStateOf(1f) }
    var currentTitleAlpha by remember { mutableFloatStateOf(0f) }

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedTrackName by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    if (showBottomSheet) {
        TrackOptionsBottomSheet(
            trackName = selectedTrackName,
            sheetState = sheetState,
            onDismissRequest = {
                showBottomSheet = false
            }
        )
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val newImageSize = getNewImageSize(currentImageSize, available)
                val previousImageSize = currentImageSize

                currentImageSize = newImageSize.coerceIn(minImageSize, maxImageSize)
                val consumed = currentImageSize - previousImageSize

                imageScale = currentImageSize / maxImageSize

                val sizeDelta = (currentImageSize - minImageSize) / (maxImageSize - minImageSize)
                currentImageAlpha = sizeDelta.coerceIn(0f, 1f)
                currentTitleAlpha = getTitleAlpha(currentImageAlpha)

                return Offset(0f, consumed.value)
            }
        }
    }

    Box(modifier = modifier.nestedScroll(nestedScrollConnection)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
                .offset {
                    IntOffset(0, currentImageSize.roundToPx())
                },
        ) {
            items(tracks) { track ->
                TrackItem(
                    track = track,
                    onTrackClicked = { selectedTrack ->
                        onTrackClicked(selectedTrack.id)
                    },
                    onMoreOptionsClicked = { selectedTrack ->
                        showBottomSheet = true
                        selectedTrackName = selectedTrack.name
                    })
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxImageSize)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    scaleX = imageScale
                    scaleY = imageScale
                    translationY = -(maxImageSize.toPx() - currentImageSize.toPx()) / 2f
                    alpha = currentImageAlpha
                }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(240.dp)
            )

            HeaderSection(
                title = title,
                subtitle = subtitle,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .graphicsLayer {
                        alpha = currentImageAlpha
                    }
            )
        }

        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.graphicsLayer {
                        alpha = currentTitleAlpha
                    }
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
            ),
            modifier = Modifier.background(Color.Transparent)
        )
    }
}

@Composable
fun HeaderSection(title: String, subtitle: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun TrackItem(
    track: Track,
    modifier: Modifier = Modifier,
    onTrackClicked: (Track) -> Unit,
    onMoreOptionsClicked: (Track) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onTrackClicked(track) }
            .padding(all = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            val artists = track.artists.joinToString(", ") { it.name }

            Text(
                text = track.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (track.isExplicit) {
                    ExplicitTag()
                    Spacer(modifier = Modifier.width(4.dp))
                }

                CompositionLocalProvider(
                    LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.70f
                    )
                ) {
                    Text(
                        text = artists,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Normal,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }
            }
        }

        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.70f)
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "More options",
                modifier = Modifier
                    .size(24.dp)
                    .clickable(onClick = { onMoreOptionsClicked(track) })
            )
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrackOptionsBottomSheet(
    trackName: String,
    sheetState: SheetState,
    onDismissRequest: () -> Unit,
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxHeight(),
        sheetState = sheetState,
        onDismissRequest = onDismissRequest
    ) {
        Text(
            trackName,
            modifier = Modifier.padding(16.dp)
        )
    }
}

private fun getNewImageSize(currentImageSize: Dp, offset: Offset): Dp {
    val sensitivity = 0.3f
    return currentImageSize + (offset.y * sensitivity).dp
}

private fun getTitleAlpha(currentImageAlpha: Float): Float {
    val sensitivity = 0.3f
    return if (currentImageAlpha <= sensitivity) {
        (sensitivity - currentImageAlpha) / sensitivity
    } else {
        0f
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
fun TrackListScreenPreview() {
    SpotifyBootlegTheme {
        Surface {
            TrackListScreen(
                title = "Global Warming",
                subtitle = "Pitbull",
                imageUrl = "https://i.scdn.co/image/ab67616d0000b2732c5b24ecfa39523a75c993c4",
                tracks = listOf(
                    Track(
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
                    Track(
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
                ),
                onBackClicked = {},
                onTrackClicked = {},
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun TrackItemPreview() {
    SpotifyBootlegTheme {
        Surface {
            TrackItem(
                track = Track(
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
                onTrackClicked = {},
                onMoreOptionsClicked = {},
            )
        }
    }
}
