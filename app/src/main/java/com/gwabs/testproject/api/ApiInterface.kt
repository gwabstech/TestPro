package com.gwabs.testproject.api

import com.gwabs.testproject.data.request.LoginRequest
import com.gwabs.testproject.data.request.RefreshTokenRequest
import com.gwabs.testproject.data.request.SignupRequest
import com.gwabs.testproject.data.response.LoginResponse
import com.gwabs.testproject.data.response.Product
import com.gwabs.testproject.data.response.SignupResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("auth/login")
    suspend fun Login(@Body request: LoginRequest): retrofit2.Response<LoginResponse>

    @POST("/users/")
    suspend fun signUp(@Body request: SignupRequest): retrofit2.Response<SignupResponse>

    @POST("/auth/refresh-token")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): retrofit2.Response<LoginResponse>

    @POST("/products")
    suspend fun getProducts():retrofit2.Response<List<Product>>
}