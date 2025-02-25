package com.gwabs.testproject.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gwabs.testproject.data.response.Product
import com.gwabs.testproject.repo.ProductRepo
import kotlinx.coroutines.launch

class ProductsViewModel():ViewModel() {
   private val productRepo = ProductRepo()
    fun getProduct(
        onError:(String)-> Unit,
        onSuccess:(List<Product>,) -> Unit
    ){
        viewModelScope.launch {
            productRepo.getProduct(

                onError = {onError(it)},
                onSuccess = {
                    onSuccess(it)
                }
            )
        }

    }
}