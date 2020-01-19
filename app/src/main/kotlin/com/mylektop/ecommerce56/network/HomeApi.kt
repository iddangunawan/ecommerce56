package com.mylektop.ecommerce56.network

import com.mylektop.ecommerce56.model.Home
import io.reactivex.Observable
import retrofit2.http.GET

interface HomeApi {
    @GET("home")
    fun getHome(): Observable<List<Home>>
}