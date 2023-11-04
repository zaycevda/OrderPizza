package com.example.pizzamenu.data.repository

import com.example.pizzamenu.data.service.FoodApi
import com.example.pizzamenu.domain.model.Product
import com.example.pizzamenu.domain.repository.FoodRepository

@Suppress("UNCHECKED_CAST")
class FoodRepositoryImpl(
    private val api: FoodApi
) : FoodRepository {
    override suspend fun <T : Product> getProducts(query: String): List<T> {
        val menuItems = api.getProducts(query = query)
        return menuItems.menuItems.map { it.toProduct() as T }
    }
}