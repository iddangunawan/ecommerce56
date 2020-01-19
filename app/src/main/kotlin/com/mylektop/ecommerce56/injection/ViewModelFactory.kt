package com.mylektop.ecommerce56.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.mylektop.ecommerce56.model.database.AppDatabase
import com.mylektop.ecommerce56.ui.home.HomeListViewModel
import com.mylektop.ecommerce56.ui.login.LoginViewModel
import com.mylektop.ecommerce56.ui.post.PostListViewModel
import com.mylektop.ecommerce56.ui.productDetail.ProductDetailViewModel
import com.mylektop.ecommerce56.ui.purchaseHistory.PurchaseHistoryViewModel
import com.mylektop.ecommerce56.ui.search.SearchViewModel

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts").build()
            @Suppress("UNCHECKED_CAST")
            return PostListViewModel(db.postDao()) as T
        }
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel() as T
        }
        if (modelClass.isAssignableFrom(HomeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeListViewModel() as T
        }
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel() as T
        }
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "purchase_history").build()
            return ProductDetailViewModel(db.purchaseHistoryDao()) as T
        }
        if (modelClass.isAssignableFrom(PurchaseHistoryViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "purchase_history").build()
            @Suppress("UNCHECKED_CAST")
            return PurchaseHistoryViewModel(db.purchaseHistoryDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}