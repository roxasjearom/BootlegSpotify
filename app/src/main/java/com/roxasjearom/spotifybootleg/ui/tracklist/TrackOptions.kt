package com.roxasjearom.spotifybootleg.ui.tracklist

import androidx.annotation.DrawableRes
import com.roxasjearom.spotifybootleg.R

sealed class TrackOption(val title: String, @DrawableRes val iconResId: Int) {
    data object AddToLikedSongs : TrackOption("Add to Liked Songs", R.drawable.ic_favorite)
    data object AddToPlaylist : TrackOption("Add to Playlist", R.drawable.ic_add_circle)
    data object AddToQueue : TrackOption("Add to Queue", R.drawable.ic_add_queue)
    data object GoToAlbum : TrackOption("Go to album", R.drawable.ic_album)
    data object GoToArtist : TrackOption("Go to artist", R.drawable.ic_artist)
    data object ShowSpotifyCode : TrackOption("Show Spotify Code", R.drawable.ic_barcode)
}
