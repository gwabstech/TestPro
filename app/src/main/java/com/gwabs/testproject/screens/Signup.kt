package com.gwabs.testproject.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.gwabs.testproject.viewModel.LoginViewModel
import com.gwabs.testproject.viewModel.SignUpViewModel

@Composable
fun SignUp(
    signUpViewModel: SignUpViewModel,
    onSignUp: () -> Unit
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(
                    text = "Email",
                )
            },
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(
                    text = "Name",
                )
            },
        )

        Spacer(modifier = Modifier.height(10.dp))
        MyPasswordTextField(
            modifier = Modifier,
            value = password,
            label = "Password"

        ) {
            password = it
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            signUpViewModel.signUp(email, password, name, "", onError = {
                Log.i("TAG","Error")
            }) {
                onSignUp()
            }

        }) {
            Text("Create Account")
        }
    }
}