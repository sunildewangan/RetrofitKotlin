package com.test.retrofitdatabinding.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.retrofitdatabinding.rest.APIClient
import com.test.retrofitdatabinding.rest.UsersApi
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel:ViewModel() {

    private val disposable = CompositeDisposable()
    val users = MutableLiveData<List<Data>>()
    val userLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchUsers()
    }

    private fun fetchUsers() {
        loading.value = true
        val apiInterface: UsersApi = APIClient.getInstance()!!.create(UsersApi::class.java)
        val call: Call<List<Data>> =
            apiInterface.getUsers()

        call.enqueue(object : Callback<List<Data>> {
            override fun onResponse(
                call: Call<List<Data>>,
                response: Response<List<Data>>
            ) {
                loading.value = false

                println("response = ${response.body()}")
                println("code = ${response.code()}")
                if (response.code() == 200) {
                    users.postValue(response.body())
                }
            }

            override fun onFailure(
                call: Call<List<Data>>,
                t: Throwable
            ) {
                println("inside failure = ${t.message}")
                loading.value = false
            }
        })

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}