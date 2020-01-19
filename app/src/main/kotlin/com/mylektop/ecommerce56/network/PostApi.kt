package com.mylektop.ecommerce56.network

import com.mylektop.ecommerce56.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("home")
    fun getPosts(): Observable<List<Post>>
}