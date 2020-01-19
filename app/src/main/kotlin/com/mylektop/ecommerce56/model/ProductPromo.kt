package com.mylektop.ecommerce56.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ProductPromo(@field:PrimaryKey
                        val id: Int,
                        val imageUrl: String,
                        val title: String,
                        val description: String,
                        val price: String,
                        val loved: Int) : Serializable