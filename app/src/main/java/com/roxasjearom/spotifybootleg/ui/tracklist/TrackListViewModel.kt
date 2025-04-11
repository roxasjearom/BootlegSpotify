package com.roxasjearom.spotifybootleg.ui.tracklist

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
class TrackListViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _trackListUiState = MutableStateFlow(TrackListUiState())
    val trackListUiState: StateFlow<TrackListUiState> = _trackListUiState.asStateFlow()

    init {
        val id = savedStateHandle.toRoute<Route.TrackList>().id
        getTrackList(id)
    }

    private fun getTrackList(id: String) {
        viewModelScope.launch {
            val albumDetails = mainRepository.getAlbumDetails(id)
            _trackListUiState.update {
                it.copy(
                    headerTitle = albumDetails.name,
                    imageUrl = albumDetails.imageUrl,
                    artists = albumDetails.artists,
                    tracks = albumDetails.tracks,
                )
            }
        }
    }
}
