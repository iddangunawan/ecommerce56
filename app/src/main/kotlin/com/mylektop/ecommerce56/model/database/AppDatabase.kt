package com.mylektop.ecommerce56.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.model.PurchaseHistoryDao

@Database(entities = [ProductPromo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao
}