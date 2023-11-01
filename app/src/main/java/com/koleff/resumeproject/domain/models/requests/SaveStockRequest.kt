package com.koleff.resumeproject.domain.models.requests

import com.koleff.resumeproject.data.dto.StockDto
import com.squareup.moshi.Json

data class SaveStockRequest (
    @Json(name = "stock")
    val stock: StockDto,
)
