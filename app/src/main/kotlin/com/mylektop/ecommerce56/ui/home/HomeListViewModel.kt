package com.mylektop.ecommerce56.ui.home

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.model.Home
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.network.HomeApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeListViewModel : BaseViewModel() {
    @Inject
    lateinit var homeApi: HomeApi
    lateinit var toolbar: ActionBar
    lateinit var bottomNavigation: BottomNavigationView

    val categoryListAdapter: CategoryListAdapter = CategoryListAdapter()
    val productListAdapter: ProductListAdapter = ProductListAdapter()
    val listProduct: MutableList<ProductPromo> = mutableListOf()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadHome() }

    private lateinit var subscription: Disposable

    init {
        loadHome()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadHome() {
        subscription = Observable.fromCallable { }
                .concatMap {
                    homeApi.getHome().concatMap { apiHomeList ->
                        Observable.just(apiHomeList)
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveHomeListStart() }
                .doOnTerminate { onRetrieveHomeListFinish() }
                .subscribe(
                        { result -> onRetrieveHomeListSuccess(result) },
                        { onRetrieveHomeListError() }
                )
    }

    private fun onRetrieveHomeListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveHomeListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveHomeListSuccess(homeList: List<Home>) {
        listProduct.addAll(homeList[0].data.productPromo)
        categoryListAdapter.updateList(homeList[0].data.category)
        productListAdapter.updateList(homeList[0].data.productPromo)
    }

    private fun onRetrieveHomeListError() {
        errorMessage.value = R.string.post_error
    }
}