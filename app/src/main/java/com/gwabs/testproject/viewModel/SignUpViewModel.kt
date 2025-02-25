package com.gwabs.testproject.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gwabs.testproject.data.User
import com.gwabs.testproject.repo.LoginRepo
import com.gwabs.testproject.repo.SignUpRepo
import kotlinx.coroutines.launch

class SignUpViewModel() : ViewModel() {
    val signUpRepo = SignUpRepo()

    fun signUp(
        email:String,
        password:String,
        name: String,
        avatar:String,
        onError:(String)-> Unit,
        onSuccess:(User,) -> Unit
    ){


        viewModelScope.launch {
            signUpRepo.Signup(
                email,
                password,
                name,
                avatar,
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError(it)
                }
            )
        }

    }

}


