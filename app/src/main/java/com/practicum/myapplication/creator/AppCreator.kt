package com.practicum.myapplication.creator

import com.practicum.myapplication.data.network.RetrofitNetworkClient
import com.practicum.myapplication.data.repository.TracksRepositoryImpl
import com.practicum.myapplication.domain.api.NetworkClient
import com.practicum.myapplication.domain.api.TracksRepository

object AppCreator {

    private val storage by lazy { Storage() }

    private val networkClient: NetworkClient by lazy {
        RetrofitNetworkClient(storage)
    }

    val tracksRepository: TracksRepository by lazy {
        TracksRepositoryImpl(networkClient)
    }
}