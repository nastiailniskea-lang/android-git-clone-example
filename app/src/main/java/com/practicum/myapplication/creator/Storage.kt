package com.practicum.myapplication.creator

import com.practicum.myapplication.data.dto.TrackDto

class Storage {

    private val tracks = listOf(
        TrackDto("Yesterday", "The Beatles", 125000),
        TrackDto("Here Comes The Sun", "The Beatles", 241000),
        TrackDto("No Reply", "The Beatles", 312000),
        TrackDto("Let It Be", "The Beatles", 361000),
        TrackDto("Girl", "The Beatles", 251000),
        TrackDto("Michelle", "The Beatles", 181000),
        TrackDto("Eleanor Rigby", "The Beatles", 372000),
        TrackDto("Come Together", "The Beatles", 249000)
    )

    fun search(expression: String): List<TrackDto> {
        if (expression.isBlank()) return emptyList()

        return tracks.filter {
            it.trackName.contains(expression, ignoreCase = true) ||
                    it.artistName.contains(expression, ignoreCase = true)
        }
    }
}