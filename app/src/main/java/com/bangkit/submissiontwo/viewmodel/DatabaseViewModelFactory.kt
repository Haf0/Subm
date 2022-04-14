package com.bangkit.submissiontwo.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DatabaseViewModelFactory private constructor(private val mApplication: Application): ViewModelProvider.NewInstanceFactory(){
    companion object{
        @Volatile
        private var INSTANCE: DatabaseViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): DatabaseViewModelFactory{
            if (INSTANCE == null){
                synchronized(DatabaseViewModelFactory::class.java){
                    INSTANCE = DatabaseViewModelFactory(application)
                }
            }
            return INSTANCE as DatabaseViewModelFactory
        }
    }
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}