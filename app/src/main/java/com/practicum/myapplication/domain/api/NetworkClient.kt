package com.practicum.myapplication.domain.api

import com.practicum.myapplication.data.dto.BaseResponse

interface NetworkClient {
    fun doRequest(dto: Any): BaseResponse
}