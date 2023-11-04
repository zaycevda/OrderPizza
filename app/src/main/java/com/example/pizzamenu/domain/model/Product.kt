package com.example.pizzamenu.domain.model

open class Product(
    open val id: Int,
    open val title: String,
    open val restaurantChain: String,
    open val image: String,
) {
    override fun toString() = "Product(id=$id, title=$title, restaurantChain=$restaurantChain, image=$image)"
}