package com.example.pizzamenu.app.di

import com.example.pizzamenu.data.repository.FoodRepositoryImpl
import com.example.pizzamenu.data.service.FoodApi
import com.example.pizzamenu.domain.repository.FoodRepository
import com.example.pizzamenu.domain.usecase.GetDessertsUseCase
import com.example.pizzamenu.domain.usecase.GetDrinksUseCase
import com.example.pizzamenu.domain.usecase.GetPizzasUseCase
import com.example.pizzamenu.domain.usecase.GetSushiUseCase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppModule {
    val gson: Gson
    val foodApi: FoodApi
    val foodRepository: FoodRepository
    val getDessertsUseCase: GetDessertsUseCase
    val getDrinksUseCase: GetDrinksUseCase
    val getPizzasUseCase: GetPizzasUseCase
    val getSushiUseCase: GetSushiUseCase
}

class AppModuleImpl : AppModule {
    override val gson: Gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    override val foodApi: FoodApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FoodApi::class.java)
    }

    override val foodRepository: FoodRepository by lazy { FoodRepositoryImpl(api = foodApi) }

    override val getDessertsUseCase: GetDessertsUseCase by lazy { GetDessertsUseCase(repository = foodRepository) }
    override val getDrinksUseCase: GetDrinksUseCase by lazy { GetDrinksUseCase(repository = foodRepository) }
    override val getPizzasUseCase: GetPizzasUseCase by lazy { GetPizzasUseCase(repository = foodRepository) }
    override val getSushiUseCase: GetSushiUseCase by lazy { GetSushiUseCase(repository = foodRepository) }

    private companion object {
        private const val BASE_URL = "https://api.spoonacular.com/"
    }
}