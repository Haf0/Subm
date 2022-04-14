package com.bangkit.submissiontwo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangkit.submissiontwo.api.ApiConfig
import com.bangkit.submissiontwo.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollViewModel: ViewModel() {
    private val _listFoll = MutableLiveData<ArrayList<User>>()
    val listFoll: LiveData<ArrayList<User>> = _listFoll

    fun getFollowingUsers(username: String){
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    _listFoll.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })
    }

    private val _listFollowers = MutableLiveData<ArrayList<User>>()
    val listFollowers: LiveData<ArrayList<User>> = _listFollowers

    fun getFollowersUsers(username: String){
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<ArrayList<User>> {
            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful){
                    _listFollowers.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })
    }
}