package com.koleff.resumeproject.api.wrappers

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class PaginationData(
    @field:Json(name = "limit")
    val limit: Int,
    @field:Json(name = "offset")
    val offset: Int,
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "total")
    val total: Int
) : Parcelable
