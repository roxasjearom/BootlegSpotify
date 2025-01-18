package com.roxasjearom.spotifybootleg.data.repository

import com.roxasjearom.spotifybootleg.domain.model.Album
import com.roxasjearom.spotifybootleg.domain.model.Category
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import javax.inject.Inject

class MockMainRepositoryImpl @Inject constructor() : MainRepository {

    override suspend fun getAccessToken(): String {
        return "fake_access_token"
    }

    override suspend fun getCategories(): List<Category> {
        return listOf(
            Category(
                id = "0JQ5DAqbMKFQ00XGBls6ym",
                name = "Hip-Hop",
                iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
            ),
            Category(
                id = "0JQ5DAqbMKFQ00XGBls6ym",
                name = "K-pop",
                iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
            ),
            Category(
                id = "0JQ5DAqbMKFQ00XGBls6ym",
                name = "Classical",
                iconUrl = "https://t.scdn.co/images/728ed47fc1674feb95f7ac20236eb6d7.jpeg"
            )
        )
    }

    override suspend fun getAlbums(): List<Album> {
        return listOf(
            Album(
                id = "382ObEPsp2rxGrnsizN5TX",
                name = "TRON: Legacy Reconfigured",
                imageUrl = "https://i.scdn.co/image/ab67616d00001e0226597c053b38c9cf93f8f3a9",
            ),
            Album(
                id = "1A2GTWGtFfWp7KSQTwWOyo",
                name = "Human After All",
                imageUrl = "https://i.scdn.co/image/ab67616d00001e02d8601e15fa1b4351fe1fc6ae",
            ),
            Album(
                id = "2noRn2Aes5aoNVsU6iWThc",
                name = "Discovery",
                imageUrl = "https://i.scdn.co/image/ab67616d00001e02b1f18dc3658aff286fa9f351",
            ),
        )
    }
}
