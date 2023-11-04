package com.example.pizzamenu.domain.model

data class Pizza(
    override val id: Int,
    override val title: String,
    override val restaurantChain: String,
    override val image: String
) : Product(id = id, title = title, image = image, restaurantChain = restaurantChain)