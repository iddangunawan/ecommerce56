package com.mylektop.ecommerce56.ui.purchaseHistory

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.MutableLiveData
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.model.PurchaseHistoryDao
import com.mylektop.ecommerce56.ui.home.ProductListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PurchaseHistoryViewModel(private val purchaseHistoryDao: PurchaseHistoryDao) : BaseViewModel() {
    lateinit var toolbar: ActionBar

    val productListAdapter: ProductListAdapter = ProductListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadData() }

    private lateinit var subscription: Disposable

    init {
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadData() {
        subscription = Observable.fromCallable { purchaseHistoryDao.all }
                .concatMap { dbPurchaseHistoryList ->
                    Observable.just(dbPurchaseHistoryList)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrievePurchaseHistoryListStart() }
                .doOnTerminate { onRetrievePurchaseHistoryListFinish() }
                .subscribe(
                        { result -> onRetrievePurchaseHistoryListSuccess(result) },
                        { onRetrievePurchaseHistoryListError() }
                )
    }

    private fun onRetrievePurchaseHistoryListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePurchaseHistoryListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePurchaseHistoryListSuccess(list: MutableList<ProductPromo>) {
        productListAdapter.updateList(list)
    }

    private fun onRetrievePurchaseHistoryListError() {
        errorMessage.value = R.string.post_error
    }
}