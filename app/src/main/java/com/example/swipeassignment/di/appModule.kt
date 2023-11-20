package com.example.swipeassignment.di

import com.example.swipeassignment.api.ProductApiService
import com.example.swipeassignment.data.repo.ProductRepository
import com.example.swipeassignment.data.repo.ProductRepositoryImpl
import com.example.swipeassignment.viewmodels.ProductListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module{
    single {
        Retrofit.Builder()
            .baseUrl("https://app.getswipe.in/api/public/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }

    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    viewModel{
        ProductListViewModel(get())
    }
}