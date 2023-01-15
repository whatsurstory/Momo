package com.beva.momoapplication.model

data class Result(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<ResultX>,
    val sort: String?
)