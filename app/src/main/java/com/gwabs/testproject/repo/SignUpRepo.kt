package com.gwabs.testproject.repo

import android.util.Log
import com.gwabs.testproject.api.RetrofitClient
import com.gwabs.testproject.data.User
import com.gwabs.testproject.data.request.LoginRequest
import com.gwabs.testproject.data.request.SignupRequest

class SignUpRepo() {

   suspend fun Signup(
        email:String,
        password:String,
        name:String,
        avatar:String,
        onSuccess:(User) -> Unit,
        onError:(String)-> Unit
    ){
        val signupRequest = SignupRequest(name,email,password,avatar)

       try {
           val response = RetrofitClient.apiInterface.signUp(signupRequest)

           if (response.isSuccessful && response.body() != null){


               val user = User(
                   email = response.body()!!.email,
                   password = response.body()!!.password,
                   avatar = response.body()!!.avatar,
                   name = response.body()!!.name,
                   role =  response.body()!!.role,
                   id = response.body()!!.id.toString()
               )
               onSuccess(user)
           }else{
               onError("Error")
           }

       }catch (e:Exception){
           onError(e.message.toString())
           Log.i("TAG",e.message.toString())
       }



    }
}