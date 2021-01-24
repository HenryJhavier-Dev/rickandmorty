package com.henryjhavierdev.rickandmorty.config.network

import com.henryjhavierdev.rickandmorty.util.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(logger).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val dataAccess: ApiService = retrofit.create(ApiService::class.java)

}