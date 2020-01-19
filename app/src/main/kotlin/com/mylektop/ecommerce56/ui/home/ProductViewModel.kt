package com.mylektop.ecommerce56.ui.home

import androidx.lifecycle.MutableLiveData
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.model.ProductPromo

class ProductViewModel : BaseViewModel() {
    private val imageUrl = MutableLiveData<String>()
    private val title = MutableLiveData<String>()
    private val description = MutableLiveData<String>()
    private val price = MutableLiveData<String>()
    private val loved = MutableLiveData<Int>()

    fun bind(productPromo: ProductPromo) {
        imageUrl.value = productPromo.imageUrl
        title.value = productPromo.title
        description.value = productPromo.description
        price.value = productPromo.price
        loved.value = productPromo.loved
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
}