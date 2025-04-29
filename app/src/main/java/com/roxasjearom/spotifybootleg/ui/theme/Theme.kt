package com.roxasjearom.spotifybootleg.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val SpotifyDarkColorScheme = darkColorScheme(
    primary = SpotifyGreen,
    onPrimary = Color.Black,
    secondary = SpotifyLightGray,
    onSecondary = Color.Black,
    tertiary = SpotifyLightBlack,
    background = Color.Black,
    onBackground = Color.White,
    surface = SpotifyBlack,
    onSurface = Color.White,
    error = SpotifyRed,
    onError = Color.White,
)

@Composable
fun SpotifyBootlegTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SpotifyDarkColorScheme,
        typography = Typography,
        content = content
    )
}
