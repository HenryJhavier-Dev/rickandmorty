package com.henryjhavierdev.rickandmorty.dataservice

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class ServiceBuilder<T: Any>(
    var URL_BASE: String
) {

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder().addInterceptor(logger).build()

    fun buildRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    inline fun <reified T:Any> getService(): T =
        buildRetrofit().run {
            create(T::class.java)
        }


}

//val dataAccess: ApiService = buildRetrofit().create(ApiService::class.java)

class CharacterRequest(urlBase: String): ServiceBuilder<CharacterService>(urlBase)

class EpisodeRequest(urlBase: String): ServiceBuilder<EpisodeService>(urlBase)