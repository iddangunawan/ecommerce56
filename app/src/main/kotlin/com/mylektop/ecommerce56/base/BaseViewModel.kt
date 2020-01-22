package com.mylektop.ecommerce56.base

import androidx.lifecycle.ViewModel
import com.mylektop.ecommerce56.injection.component.DaggerViewModelInjector
import com.mylektop.ecommerce56.injection.component.ViewModelInjector
import com.mylektop.ecommerce56.injection.module.NetworkModule
import com.mylektop.ecommerce56.ui.home.CategoryViewModel
import com.mylektop.ecommerce56.ui.home.HomeListViewModel
import com.mylektop.ecommerce56.ui.home.ProductViewModel
import com.mylektop.ecommerce56.ui.login.LoginViewModel
import com.mylektop.ecommerce56.ui.productDetail.ProductDetailViewModel
import com.mylektop.ecommerce56.ui.purchaseHistory.PurchaseHistoryViewModel
import com.mylektop.ecommerce56.ui.search.SearchViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is LoginViewModel -> injector.inject(this)
            is HomeListViewModel -> injector.inject(this)
            is CategoryViewModel -> injector.inject(this)
            is ProductViewModel -> injector.inject(this)
            is SearchViewModel -> injector.inject(this)
            is ProductDetailViewModel -> injector.inject(this)
            is PurchaseHistoryViewModel -> injector.inject(this)
        }
    }
}