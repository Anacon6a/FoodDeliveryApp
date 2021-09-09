package com.example.fooddeliveryapp.data.remote.api

class RestApi(private val restApiService: RestApiService) {
    suspend fun getCategories() = restApiService.getCategories()
    suspend fun getMealsByCategory(categoryName: String) = restApiService.getMealsByCategory(categoryName)
}
