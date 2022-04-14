package com.bangkit.submissiontwo.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.bangkit.submissiontwo.database.UserDatabase
import com.bangkit.submissiontwo.repository.UsersRepository

class FavoriteViewModel(application: Application): ViewModel() {
    private val mUsersRepository: UsersRepository = UsersRepository(application)

    fun getAllUsers(): LiveData<List<UserDatabase>> = mUsersRepository.getAllUsers()

    fun deleteAllUsers() = mUsersRepository.deleteAllUsers()
}