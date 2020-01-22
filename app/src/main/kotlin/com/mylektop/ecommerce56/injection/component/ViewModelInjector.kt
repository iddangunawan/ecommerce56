package com.mylektop.ecommerce56.injection.component

import com.mylektop.ecommerce56.injection.module.NetworkModule
import com.mylektop.ecommerce56.ui.home.CategoryViewModel
import com.mylektop.ecommerce56.ui.home.HomeListViewModel
import com.mylektop.ecommerce56.ui.home.ProductViewModel
import com.mylektop.ecommerce56.ui.login.LoginViewModel
import com.mylektop.ecommerce56.ui.productDetail.ProductDetailViewModel
import com.mylektop.ecommerce56.ui.purchaseHistory.PurchaseHistoryViewModel
import com.mylektop.ecommerce56.ui.search.SearchViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified LoginViewModel.
     * @param loginViewModel LoginViewModel in which to inject the dependencies
     */
    fun inject(loginViewModel: LoginViewModel)

    /**
     * Injects required dependencies into the specified HomeListViewModel.
     * @param homeListViewModel HomeListViewModel in which to inject the dependencies
     */
    fun inject(homeListViewModel: HomeListViewModel)

    /**
     * Injects required dependencies into the specified CategoryViewModel.
     * @param categoryViewModel CategoryViewModel in which to inject the dependencies
     */
    fun inject(categoryViewModel: CategoryViewModel)

    /**
     * Injects required dependencies into the specified ProductViewModel.
     * @param productViewModel ProductViewModel in which to inject the dependencies
     */
    fun inject(productViewModel: ProductViewModel)

    /**
     * Injects required dependencies into the specified SearchViewModel.
     * @param searchViewModel SearchViewModel in which to inject the dependencies
     */
    fun inject(searchViewModel: SearchViewModel)

    /**
     * Injects required dependencies into the specified ProductDetailViewModel.
     * @param productDetailViewModel ProductDetailViewModel in which to inject the dependencies
     */
    fun inject(productDetailViewModel: ProductDetailViewModel)

    /**
     * Injects required dependencies into the specified PurchaseHistoryViewModel.
     * @param purchaseHistoryViewModel PurchaseHistoryViewModel in which to inject the dependencies
     */
    fun inject(purchaseHistoryViewModel: PurchaseHistoryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}