package com.practicum.myapplication.ui.state

import com.practicum.myapplication.domain.model.Track

sealed interface SearchState {
    data object Initial : SearchState
    data object Searching : SearchState
    data class Success(val foundList: List<Track>) : SearchState
    data class Empty(val message: String) : SearchState
    data class Fail(val error: String) : SearchState
}