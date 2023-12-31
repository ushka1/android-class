package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

@Serializable
data class Product(
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val price: Float? = null,
    val categoryCode: String? = null,
)

object Products : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val description = varchar("description", 1024)
    val price = float("price")
    val categoryCode = varchar("categoryCode", 128).references(
        Categories.code,
        onDelete = ReferenceOption.CASCADE
    )

    override val primaryKey = PrimaryKey(id)
}

