package com.gwabs.testproject.repo

import android.util.Log
import com.gwabs.testproject.api.RetrofitClient
import com.gwabs.testproject.data.request.LoginRequest

class LoginRepo() {

   suspend fun Login(
        email:String,
        password:String,
        onSuccess:(String,String) -> Unit,
        onError:(String)-> Unit
    ){
        val loginRequest = LoginRequest(email,password)

       try {
           val response = RetrofitClient.apiInterface.Login(loginRequest)

           if (response.isSuccessful && response.body() != null){

               val access_token = response.body()!!.access_token
               val refresh_token = response.body()!!.refresh_token

               onSuccess(access_token,refresh_token)
           }

       }catch (e:Exception){
           onError(e.message.toString())
           Log.i("TAG",e.message.toString())
       }



    }
}