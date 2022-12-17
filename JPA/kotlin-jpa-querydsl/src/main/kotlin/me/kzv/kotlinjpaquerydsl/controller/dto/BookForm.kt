package me.kzv.kotlinjpaquerydsl.controller.dto

data class BookForm(
        val id: Long?,

        val name: String,
        val price: Int,
        val stockQuantity: Int,

        val author: String,
        val isbn: String,
)