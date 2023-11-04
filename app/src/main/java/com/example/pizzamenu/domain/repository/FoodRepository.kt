package com.example.pizzamenu.domain.repository

import com.example.pizzamenu.domain.model.Product

interface FoodRepository {
    suspend fun <T : Product> getProducts(query: String): List<T>
}