package com.gwabs.testproject.repo

import android.util.Log
import com.gwabs.testproject.api.RetrofitClient
import com.gwabs.testproject.data.request.LoginRequest
import org.json.JSONObject

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

               Log.i("TAG","Success")
               onSuccess(access_token,refresh_token)
           }else{

               val errorBodyString = response.errorBody()?.string()
               // Parse error message from JSON if available
               val errorMessage = if (!errorBodyString.isNullOrEmpty()) {
                   try {
                       // Using JSONObject to parse the error message field
                       JSONObject(errorBodyString).optString("message", "Unknown error")
                   } catch (e: Exception) {
                       "Unknown error"
                   }
               } else {
                   "Unknown error"
               }
               onError(response.errorBody().toString())
               Log.i("TAG",errorMessage)
           }

       }catch (e:Exception){
           onError(e.message.toString())
           Log.i("TAG",e.message.toString())
       }



    }
}