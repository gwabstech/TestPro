package com.gwabs.testproject.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gwabs.testproject.screens.Login
import com.gwabs.testproject.screens.ProductListScreen
import com.gwabs.testproject.screens.SignUp
import com.gwabs.testproject.viewModel.LoginViewModel
import com.gwabs.testproject.viewModel.ProductsViewModel
import com.gwabs.testproject.viewModel.SignUpViewModel

@Composable
fun HomeNavigator(
    context: Context,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    productsViewModel: ProductsViewModel,

) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "LoginScreen") {
        composable("LoginScreen") { Login(loginViewModel,navController)  {
            navController.navigate("Product")
        }}
        composable("SignUp") { SignUp(signUpViewModel) {
            navController.navigate("Product")
        } }
        composable("Product") {
            ProductListScreen(productsViewModel)
        }
    }

}