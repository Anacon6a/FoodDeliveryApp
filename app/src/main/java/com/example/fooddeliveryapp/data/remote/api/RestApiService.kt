package com.example.fooddeliveryapp.data.remote.api

import com.example.fooddeliveryapp.data.remote.dto.CategoriesRemote
import com.example.fooddeliveryapp.data.remote.dto.MealsRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApiService {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): Response<CategoriesRemote>

    @GET("/api/json/v1/1/filter.php")
    suspend fun getMealsByCategory(@Query("c") categoryName: String): Response<MealsRemote>
}