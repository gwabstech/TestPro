package com.gwabs.testproject.api




import com.google.gson.GsonBuilder
import com.gwabs.testproject.Utils.BASE_URL

import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofitClient : Retrofit.Builder by lazy{

        val gson = GsonBuilder()
            .setLenient()
            .create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
           // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}