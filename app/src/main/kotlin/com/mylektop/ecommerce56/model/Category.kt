package com.mylektop.ecommerce56.model

import androidx.room.Entity

@Entity
data class Category(val imageUrl: String,
                    val id: Int,
                    val name: String)
