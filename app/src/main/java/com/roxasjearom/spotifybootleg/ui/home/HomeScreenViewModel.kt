package com.roxasjearom.spotifybootleg.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val mainRepository: MainRepository,
) : ViewModel() {

    private val _homeUiState = MutableStateFlow(HomeScreenUiState())
    val homeUiState: StateFlow<HomeScreenUiState> = _homeUiState.asStateFlow()

    init {
        getCategories()
        getAlbums()
        getArtists()
    }

    private fun getCategories() {
        viewModelScope.launch {
            val categories = mainRepository.getCategories()
            _homeUiState.update {
                it.copy(categories = categories)
            }
        }
    }

    private fun getAlbums() {
        viewModelScope.launch {
            val albums = mainRepository.getAlbums(listOf(
                "382ObEPsp2rxGrnsizN5TX",
                "1A2GTWGtFfWp7KSQTwWOyo",
                "2noRn2Aes5aoNVsU6iWThc"
            ))
            _homeUiState.update {
                it.copy(albums = albums)
            }
        }
    }

    private fun getArtists() {
        viewModelScope.launch {
            val artists = mainRepository.getArtists(
                listOf(
                    "2CIMQHirSU0MQqyYHq0eOx",
                    "57dN52uHvrHOxijzpIgu3E",
                    "1vCWHaC5f2uS3yhpwWbIA6"
                )
            )
            _homeUiState.update {
                it.copy(artists = artists)
            }
        }
    }
}
