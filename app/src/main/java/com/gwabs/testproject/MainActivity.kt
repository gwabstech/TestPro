package com.gwabs.testproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.gwabs.testproject.repo.LoginRepo
import com.gwabs.testproject.ui.HomeNavigator
import com.gwabs.testproject.ui.theme.TestProjectTheme
import com.gwabs.testproject.viewModel.LoginViewModel
import com.gwabs.testproject.viewModel.ProductsViewModel
import com.gwabs.testproject.viewModel.SignUpViewModel

class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var signUpViewModel: SignUpViewModel
    private lateinit var productViewModel: ProductsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        signUpViewModel = SignUpViewModel()
        productViewModel = ProductsViewModel()
        val sharedPreferences = getSharedPreferences("APP", Context.MODE_PRIVATE)
        val factory = LoginViewModel.LoginViewModelFactory(sharedPreferences)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

                HomeNavigator(this,loginViewModel,signUpViewModel,productViewModel)

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestProjectTheme {
        Greeting("Android")
    }
}