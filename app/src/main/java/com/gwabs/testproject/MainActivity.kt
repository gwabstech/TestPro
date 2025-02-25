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
import com.gwabs.testproject.ui.theme.TestProjectTheme
import com.gwabs.testproject.viewModel.LoginViewModel

class MainActivity : ComponentActivity() {

    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val sharedPreferences = getSharedPreferences("APP", Context.MODE_PRIVATE)
        val factory = LoginViewModel.LoginViewModelFactory(sharedPreferences)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val loginRepo = LoginRepo()
        setContent {
            TestProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier
                            .padding(innerPadding)
                            .clickable {
                                loginViewModel.login("john@mail.com", "changeme", onError = {

                                }, onSuccess = {
                                    Log.i("TAG","this is the data it $it")
                                })
                            }
                    )
                }
            }
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