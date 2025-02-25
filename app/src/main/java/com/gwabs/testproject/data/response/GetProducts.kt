package com.gwabs.testproject.data.response

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val category: Category,
    val images: List<String>
)

@Serializable
data class Category(
    val id: Int,
    val name: String,
    val image: String
)