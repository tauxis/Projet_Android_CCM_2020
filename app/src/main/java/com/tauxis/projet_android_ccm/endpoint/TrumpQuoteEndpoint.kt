package com.tauxis.projet_android_ccm.endpoint

import com.tauxis.projet_android_ccm.model.TrumpQuote
import retrofit2.http.GET
import retrofit2.http.Headers

interface TrumpQuoteEndpoint {

    @Headers(
        "Accept: application/json",
        "Accept: */*"
    )
    @GET("random/quote")
    suspend fun getRandomQuote(): TrumpQuote
}