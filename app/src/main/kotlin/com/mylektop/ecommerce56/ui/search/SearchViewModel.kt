package com.mylektop.ecommerce56.ui.search

import androidx.appcompat.app.ActionBar
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.ui.home.ProductListAdapter

class SearchViewModel : BaseViewModel() {
    lateinit var toolbar: ActionBar
    val productListAdapter: ProductListAdapter = ProductListAdapter()
}