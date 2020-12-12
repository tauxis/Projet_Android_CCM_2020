package com.tauxis.projet_android_ccm.architecture

import com.google.gson.GsonBuilder
import com.tauxis.projet_android_ccm.endpoint.TrumpQuoteEndpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.tronalddump.io/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getTrumpQuote(): TrumpQuoteEndpoint = retrofit.create(TrumpQuoteEndpoint::class.java)
}