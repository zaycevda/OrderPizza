package com.example.pizzamenu.data.service

import com.example.pizzamenu.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FoodApi {
    @GET("food/menuItems/search")
    suspend fun getProducts(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): ProductResponse

    private companion object {
        private const val API_KEY = "88ac4d71631d422bac6a89fc63759401"
    }
}