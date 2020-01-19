package com.mylektop.ecommerce56.model

import androidx.room.Entity

@Entity
data class Data(val category: MutableList<Category>,
                val productPromo: MutableList<ProductPromo>)