package com.example.fooddeliveryapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CategoryRemote(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val image: String,
    @SerializedName("strCategoryDescription")
    val description: String
)