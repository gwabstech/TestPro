package com.gwabs.testproject.repo

import android.util.Log
import com.gwabs.testproject.api.RetrofitClient
import com.gwabs.testproject.data.request.RefreshTokenRequest
import com.gwabs.testproject.data.response.Product

class TokenRefreshReop {


    suspend fun refreshToken(
        refreshToken:String,
        onSuccess:(String, String) -> Unit,
        onError:(String)-> Unit
    ){

        val request = RefreshTokenRequest(refreshToken)

        try {
            val response = RetrofitClient.apiInterface.refreshToken(request)

            if (response.isSuccessful && response.body() != null){

                onSuccess(response.body()!!.access_token,response.body()!!.refresh_token)
            }

        }catch (e:Exception){
            onError(e.message.toString())
            Log.i("TAG",e.message.toString())
        }



    }
}