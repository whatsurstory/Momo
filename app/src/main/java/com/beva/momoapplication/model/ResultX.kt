package com.beva.momoapplication.model

import com.squareup.moshi.Json

data class ResultX(
    @Json(name = "_id") val id: Int,
    @Json(name = "_importdate") val importDate: ImportDate,
    @Json(name = "e_category") val category: String,
    @Json(name = "e_geo") val geo: String,
    @Json(name = "e_info") val info: String,
    @Json(name = "e_memo") val memo: String?,
    @Json(name = "e_name") val name: String,
    @Json(name = "e_no") val no: String,
    @Json(name = "e_pic_url") val picUrl: String,
    @Json(name = "e_url") val url: String
)