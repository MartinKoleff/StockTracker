package com.koleff.resumeproject.common

object Constants {
    const val API_KEY = "38759d046e883748e2947cc8a1bd4665"

    const val SCHEME_LOCAL = "http"
    const val SCHEME = "https"

    const val BASE_URL = "api.marketstack.com"
    const val FAKE_BASE_URL = "demo6160472.mockable.io"

    const val BASE_LOCAL_URL = "https://localhost"

    const val BASE_URL_FULL = "$SCHEME_LOCAL://$BASE_URL/" //v1/eod?access_key=$API_KEY&symbols=AAPL
    const val FAKE_BASE_URL_FULL = "$SCHEME_LOCAL://$FAKE_BASE_URL/"

    const val useFakeApi = true
}
