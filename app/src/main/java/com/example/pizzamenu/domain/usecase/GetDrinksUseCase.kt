package com.example.pizzamenu.domain.usecase

import com.example.pizzamenu.domain.model.Drink
import com.example.pizzamenu.domain.repository.FoodRepository

class GetDrinksUseCase(private val repository: FoodRepository) {
    suspend fun execute() = repository.getProducts<Drink>(query = "drink")
}