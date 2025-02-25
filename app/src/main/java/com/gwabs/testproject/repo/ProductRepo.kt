package com.gwabs.testproject.repo

import android.util.Log
import com.gwabs.testproject.api.RetrofitClient
import com.gwabs.testproject.data.request.LoginRequest
import com.gwabs.testproject.data.response.Product


class ProductRepo() {

    suspend fun getProduct(
        onSuccess:(List<Product>) -> Unit,
        onError:(String)-> Unit
    ){


        try {
            val response = RetrofitClient.apiInterface.getProducts()

            if (response.isSuccessful && response.body() != null){



                onSuccess(response.body()!!)
            }

        }catch (e:Exception){
            onError(e.message.toString())
            Log.i("TAG",e.message.toString())
        }



    }
}