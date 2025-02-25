package com.gwabs.testproject.viewModel

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.gwabs.testproject.repo.LoginRepo
import kotlinx.coroutines.launch

class LoginViewModel(appPreference: SharedPreferences) : ViewModel() {


    private val loginRepo = LoginRepo()

    fun login(
        email:String,
        password:String,
        onError:(String)-> Unit,
        onSuccess:(String,) -> Unit
    ){


        viewModelScope.launch {
            loginRepo.Login(
                email,
                password,
                onError = {onError(it)},
                onSuccess = {access_token, ref_token ->
                    onSuccess("Login Successfully ")

                    Log.i("TAG", "Access token $access_token, ref_token $ref_token")
                }
            )
        }

    }




    class LoginViewModelFactory(private val preference: SharedPreferences) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(preference) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}


