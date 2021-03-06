package com.example.fooddeliveryapp.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val restApiService: RestApiService = getRetrofit().create(RestApiService::class.java)
}