package com.mylektop.ecommerce56.ui.home

import androidx.lifecycle.MutableLiveData
import com.mylektop.ecommerce56.base.BaseViewModel
import com.mylektop.ecommerce56.model.Category

class CategoryViewModel : BaseViewModel() {
    private val name = MutableLiveData<String>()
    private val imageUrl = MutableLiveData<String>()

    fun bind(category: Category) {
        name.value = category.name
        imageUrl.value = category.imageUrl
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }
}