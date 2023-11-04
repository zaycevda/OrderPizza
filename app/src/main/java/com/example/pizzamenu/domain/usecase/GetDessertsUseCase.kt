package com.example.pizzamenu.domain.usecase

import com.example.pizzamenu.domain.model.Dessert
import com.example.pizzamenu.domain.repository.FoodRepository

class GetDessertsUseCase(private val repository: FoodRepository) {
    suspend fun execute() = repository.getProducts<Dessert>(query = "dessert")
}