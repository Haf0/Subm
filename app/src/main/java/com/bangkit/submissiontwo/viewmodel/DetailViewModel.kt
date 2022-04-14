package com.bangkit.submissiontwo.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.submissiontwo.api.ApiConfig
import com.bangkit.submissiontwo.database.UserDatabase
import com.bangkit.submissiontwo.model.DetailUser
import com.bangkit.submissiontwo.repository.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(application: Application): ViewModel() {
    private val _detailUser = MutableLiveData<DetailUser>()
    val detailUser: LiveData<DetailUser> = _detailUser


    private val mUsersRepository: UsersRepository = UsersRepository(application)


    fun getDetailUser(username: String){
        val client = ApiConfig.getApiService().getDetailUserbyUsername(username)
        client.enqueue(object : Callback<DetailUser>{
            override fun onResponse(call: Call<DetailUser>, response: Response<DetailUser>) {
                if (response.isSuccessful){
                    _detailUser.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<DetailUser>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })
    }



    fun insert(user: UserDatabase){
        mUsersRepository.insert(user)
    }

    fun delete(user: UserDatabase){
        mUsersRepository.delete(user)
    }
    fun getAlluser(): LiveData<List<UserDatabase>> = mUsersRepository.getAllUsers()

}