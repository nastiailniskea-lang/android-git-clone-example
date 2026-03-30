package com.practicum.myapplication.data.network

import com.practicum.myapplication.creator.Storage
import com.practicum.myapplication.data.dto.BaseResponse
import com.practicum.myapplication.data.dto.TracksSearchRequest
import com.practicum.myapplication.data.dto.TracksSearchResponse
import com.practicum.myapplication.domain.api.NetworkClient

class RetrofitNetworkClient(
    private val storage: Storage
) : NetworkClient {

    override fun doRequest(dto: Any): BaseResponse {
        return when (dto) {
            is TracksSearchRequest -> {
                TracksSearchResponse(storage.search(dto.expression)).apply {
                    resultCode = 200
                }
            }
            else -> {
                BaseResponse().apply {
                    resultCode = 400
                }
            }
        }
    }
}