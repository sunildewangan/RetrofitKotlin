package com.test.retrofitdatabinding.rest

import com.test.retrofitdatabinding.model.Data
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {
    @GET("todos")
    public fun getUsers(): Call<List<Data>>
}