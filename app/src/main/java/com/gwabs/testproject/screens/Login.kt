package com.gwabs.testproject.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gwabs.testproject.R
import com.gwabs.testproject.viewModel.LoginViewModel

@Composable
fun Login(
    loginViewModel: LoginViewModel,
    navController: NavController,
    onLoggedIn:() -> Unit
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {


        Image(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.Person,
            contentDescription = "",
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
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
        MyPasswordTextField(
            modifier = Modifier,
            value = password,
            label = "Password"

        ) {
            password = it
        }


        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            isLoading = true
            loginViewModel.login(email,password, onError = {
                isLoading = false
                Log.i("TAG","the error $it")
            }, onSuccess = {
                isLoading = false
                Log.i("TAG",it)
                onLoggedIn()
            })

        }) {
           Text("Login")
        }

        if(isLoading){
            CircularProgressIndicator()
        }
        Text("Dont have account Register now", modifier = Modifier.clickable { navController.navigate("SignUp") })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onValueChanged: (String) -> Unit,
) {

    var isValid by remember { mutableStateOf(true) }
    var passwordVisible by remember { mutableStateOf(false) }  // Manage password visibility
    // The OutlinedTextField
    Column {

        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChanged(it)
                isValid = it.length >= 4
            },
            label = {
                Text(
                    text = label,

                )
            },
            visualTransformation = if (!passwordVisible) {
                PasswordVisualTransformation()  // Hide text for password
            } else {
                VisualTransformation.None  // Show text
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),


            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black,
                focusedTextColor = Color.Black,

            ),
            trailingIcon = {

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) {
                                R.drawable.baseline_visibility_24
                            } else {
                                R.drawable.baseline_visibility_off_24
                            }
                        ),
                        contentDescription = if (passwordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        }
                    )

                }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        )

    }

    if (!isValid) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .basicMarquee()
                .padding(start = 20.dp, end = 20.dp),
            text = "Invalid Password",
            color = Color.Red,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}
