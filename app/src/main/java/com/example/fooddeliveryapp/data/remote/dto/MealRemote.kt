package com.example.fooddeliveryapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MealRemote(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val image: String
)