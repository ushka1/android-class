package com.example.dbshopping.repositories

import com.example.dbshopping.models.Category
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CategoryRepository private constructor() {
    companion object {
        @Volatile
        private var INSTANCE: CategoryRepository? = null

        fun getInstance(): CategoryRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = CategoryRepository()
                INSTANCE = instance
                instance
            }
        }
    }

    private val db = FirebaseFirestore.getInstance()
    private val categoryCollection = db.collection("categories")

    suspend fun addCategory(category: Category): String {
        val document = categoryCollection.add(category).await()
        return document.id
    }

    suspend fun getCategory(id: String): Category? {
        val document = categoryCollection.document(id).get().await()

        return if (document.exists()) {
            document.toObject(Category::class.java)
        } else null
    }

    suspend fun getAllCategorys(): List<Category> {
        val query = categoryCollection.get().await()
        return query.toObjects(Category::class.java)
    }

    suspend fun updateCategory(category: Category) {
        category.id?.let {
            categoryCollection.document(it).set(category).await()
        }
    }

    suspend fun deleteCategory(id: String) {
        categoryCollection.document(id).delete().await()
    }
}