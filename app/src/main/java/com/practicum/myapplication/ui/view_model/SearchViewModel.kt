package com.practicum.myapplication.ui.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.practicum.myapplication.domain.api.TracksRepository
import com.practicum.myapplication.ui.state.SearchState

class SearchViewModel(
    private val tracksRepository: TracksRepository
) : ViewModel() {

    private val _searchScreenState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchScreenState: StateFlow<SearchState> = _searchScreenState.asStateFlow()

    fun search(expression: String) {
        if (expression.isBlank()) {
            _searchScreenState.value = SearchState.Initial
            return
        }

        viewModelScope.launch {
            _searchScreenState.value = SearchState.Searching

            try {
                val tracks = tracksRepository.searchTracks(expression)

                _searchScreenState.value = if (tracks.isEmpty()) {
                    SearchState.Empty("Ничего не найдено")
                } else {
                    SearchState.Success(tracks)
                }
            } catch (e: Exception) {
                _searchScreenState.value = SearchState.Fail(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }
}