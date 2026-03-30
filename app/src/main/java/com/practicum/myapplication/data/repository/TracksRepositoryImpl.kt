package com.practicum.myapplication.data.repository

import kotlinx.coroutines.delay
import com.practicum.myapplication.data.dto.TrackDto
import com.practicum.myapplication.data.dto.TracksSearchRequest
import com.practicum.myapplication.data.dto.TracksSearchResponse
import com.practicum.myapplication.domain.api.NetworkClient
import com.practicum.myapplication.domain.api.TracksRepository
import com.practicum.myapplication.domain.model.Track

class TracksRepositoryImpl(
    private val networkClient: NetworkClient
) : TracksRepository {

    override suspend fun searchTracks(expression: String): List<Track> {
        val response = networkClient.doRequest(TracksSearchRequest(expression))

        delay(1000)

        return if (response.resultCode == 200 && response is TracksSearchResponse) {
            response.results.map { map(it) }
        } else {
            emptyList()
        }
    }

    private fun map(dto: TrackDto): Track {
        val totalSeconds = dto.trackTimeMillis / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        val trackTime = "%02d:%02d".format(minutes, seconds)

        return Track(
            trackName = dto.trackName,
            artistName = dto.artistName,
            trackTime = trackTime
        )
    }
}