package com.example.pizzamenu.domain.usecase

import com.example.pizzamenu.domain.model.Pizza
import com.example.pizzamenu.domain.repository.FoodRepository

class GetPizzasUseCase(private val repository: FoodRepository) {
    suspend fun execute() = repository.getProducts<Pizza>(query = "pizza")
}