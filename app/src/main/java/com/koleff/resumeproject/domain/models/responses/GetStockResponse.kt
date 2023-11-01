package com.koleff.resumeproject.domain.models.responses

import com.koleff.resumeproject.data.dto.StockDto
import com.koleff.resumeproject.domain.models.responses.baseResponse.BaseResponse
import com.squareup.moshi.Json

data class GetStockResponse (
    @Json(name = "data")
    val stock: StockDto,
): BaseResponse()