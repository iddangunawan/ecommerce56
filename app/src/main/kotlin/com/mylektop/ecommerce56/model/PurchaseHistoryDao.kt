package com.mylektop.ecommerce56.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PurchaseHistoryDao {
    @get:Query("SELECT * FROM productpromo")
    val all: MutableList<ProductPromo>

    @Insert
    fun insertAll(vararg products: ProductPromo)
}