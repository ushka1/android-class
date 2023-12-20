package com.example.netshopping.models

data class Product(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var price: Double? = null,
    var categoryId: String? = null
)