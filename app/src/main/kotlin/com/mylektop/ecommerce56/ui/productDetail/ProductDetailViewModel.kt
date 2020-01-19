package com.mylektop.ecommerce56.ui.productDetail

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.MutableLiveData
import com.mylektop.ecommerce56.R
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.model.ProductPromo
import com.mylektop.ecommerce56.model.PurchaseHistoryDao
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductDetailViewModel(private val purchaseHistoryDao: PurchaseHistoryDao) : BaseViewModel() {
    lateinit var toolbar: ActionBar

    private var productPromo: ProductPromo? = null

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val successMessage: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { insertToPurchaseHistory() }

    private val id = MutableLiveData<Int>()
    private val imageUrl = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val price = MutableLiveData<String>()
    private val loved = MutableLiveData<Int>()

    private lateinit var subscription: Disposable

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized)
            subscription.dispose()
    }

    fun insertToPurchaseHistory() {
        subscription = Observable.just(productPromo)
                .concatMap {
                    Observable.just(purchaseHistoryDao.insertAll(it))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveInsertPurchaseHistoryStart() }
                .doOnTerminate { onRetrieveInsertPurchaseHistoryFinish() }
                .subscribe(
                        { onRetrieveInsertPurchaseHistorySuccess() },
                        { onRetrieveInsertPurchaseHistoryError() }
                )
    }

    fun bind(productPromo: ProductPromo) {
        this.productPromo = productPromo
        id.value = productPromo.id
        imageUrl.value = productPromo.imageUrl
        title.value = productPromo.title
        description.value = productPromo.description
        price.value = productPromo.price
        loved.value = productPromo.loved
    }

    fun getId(): MutableLiveData<Int> {
        return id
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }

    fun getTitle(): MutableLiveData<String> {
        return title
    }

    fun getDescription(): MutableLiveData<String> {
        return description
    }

    fun getPrice(): MutableLiveData<String> {
        return price
    }

    fun getLoved(): MutableLiveData<Int> {
        return loved
    }

    private fun onRetrieveInsertPurchaseHistoryStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveInsertPurchaseHistoryFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveInsertPurchaseHistorySuccess() {
        successMessage.value = R.string.insert_purchase_history_success
    }

    private fun onRetrieveInsertPurchaseHistoryError() {
        errorMessage.value = R.string.insert_purchase_history_error
    }
}