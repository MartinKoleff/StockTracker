package com.koleff.resumeproject.common

object Constants {
    const val API_KEY = "cf3ee1fcf45260ed96bf958093517b6d"

    const val SCHEME_LOCAL = "http"
    const val SCHEME = "https"
    const val PORT = 8081

    const val BASE_URL = "api.marketstack.com"
    const val FAKE_BASE_URL = "demo6160472.mockable.io"

    const val BASE_LOCAL_URL = "localhost"
    const val BASE_LOCAL_URL_FULL = "$SCHEME_LOCAL://$BASE_LOCAL_URL"
    const val BASE_LOCAL_URL_FULL_PORT = "$SCHEME_LOCAL://$BASE_LOCAL_URL:$PORT/"

    const val BASE_URL_FULL = "$SCHEME_LOCAL://$BASE_URL/"
    const val FAKE_BASE_URL_FULL = "$SCHEME_LOCAL://$FAKE_BASE_URL/"

    const val useFakeApi = false
}
