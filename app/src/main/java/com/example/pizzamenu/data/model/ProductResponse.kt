package com.example.pizzamenu.data.model

import com.example.pizzamenu.domain.model.Product

data class ProductResponse(
    val type: String,
    val menuItems: List<ProductDto>
) {
    data class ProductDto(
        val id: Int,
        val title: String,
        val restaurantChain: String,
        val image: String
    ) {
        fun toProduct() =
            Product(
                id = id,
                title = title,
                restaurantChain = restaurantChain,
                image = image
            )
    }
}