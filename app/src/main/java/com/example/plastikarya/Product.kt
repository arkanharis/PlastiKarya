package com.example.plastikarya

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val rating: Float = 0f,
    val soldCount: Int = 0,
    val description: String = "",
    val category: String = "",
    val sellerId: Int = 0,
    val sellerName: String = "",
    val isAvailable: Boolean = true
)