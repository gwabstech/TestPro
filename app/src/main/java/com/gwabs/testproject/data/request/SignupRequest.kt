package com.gwabs.testproject.data.request

data class SignupRequest(
    val name:String,
    val email:String,
    val password:String,
    val avatar:String = "https://picsum.photos/800"
)


