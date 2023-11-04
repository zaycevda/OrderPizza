package com.example.pizzamenu.domain.usecase

import com.example.pizzamenu.domain.model.Sushi
import com.example.pizzamenu.domain.repository.FoodRepository

class GetSushiUseCase(private val repository: FoodRepository) {
    suspend fun execute() = repository.getProducts<Sushi>(query = "sushi")
}