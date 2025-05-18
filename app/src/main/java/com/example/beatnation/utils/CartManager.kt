package com.example.beatnation.utils

import com.example.beatnation.model.Product

object CartManager {
    private val cartItems = mutableListOf<Product>()

    fun addProduct(product: Product) {
        cartItems.add(product)
    }

    fun getProducts(): List<Product> = cartItems

    fun getTotal(): Double = cartItems.sumOf { it.price }

    fun clearCart() {
        cartItems.clear()
    }
}
