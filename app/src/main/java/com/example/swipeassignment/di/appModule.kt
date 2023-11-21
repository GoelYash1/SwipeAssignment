package com.example.swipeassignment.di

import android.util.Log
import com.example.swipeassignment.api.ProductApi
import com.example.swipeassignment.data.repo.ProductRepository
import com.example.swipeassignment.data.repo.ProductRepositoryImpl
import com.example.swipeassignment.viewmodels.ProductAddViewModel
import com.example.swipeassignment.viewmodels.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{
    Log.d("In App Module","We are in app Module")
    single {
        Retrofit.Builder()
            .baseUrl("https://app.getswipe.in/api/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    viewModel{
        ProductListViewModel(get())
    }

    viewModel{
        ProductAddViewModel(get())
    }
}