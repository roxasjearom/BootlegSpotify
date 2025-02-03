package com.roxasjearom.spotifybootleg.ui.songlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.roxasjearom.spotifybootleg.domain.repository.MainRepository
import com.roxasjearom.spotifybootleg.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _songListUiState = MutableStateFlow(SongListUiState())
    val songListUiState: StateFlow<SongListUiState> = _songListUiState.asStateFlow()

    init {
        val id = savedStateHandle.toRoute<Route.SongList>().id
        getSongList(id)
    }

    private fun getSongList(id: String) {
        viewModelScope.launch {
            val albumDetails = mainRepository.getAlbumDetails(id)
            _songListUiState.update {
                it.copy(
                    headerTitle = albumDetails.name,
                    imageUrl = albumDetails.imageUrl,
                    artists = albumDetails.artists,
                    songs = albumDetails.songs,
                )
            }
        }
    }

}
