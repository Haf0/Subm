package com.bangkit.submissiontwo.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.bangkit.submissiontwo.SettingPreferences
import com.bangkit.submissiontwo.api.ApiConfig
import com.bangkit.submissiontwo.model.ListUserResponse
import com.bangkit.submissiontwo.model.User
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences) : ViewModel() {
    private val _list = MutableLiveData<List<User>>()
    val list: LiveData<List<User>> = _list


    fun getUserFromSearch(query: String){
        val client = ApiConfig.getApiService().getListUser(query)
        client.enqueue(object : Callback<ListUserResponse>{
            override fun onResponse(
                call: Call<ListUserResponse>,
                response: Response<ListUserResponse>
            ) {
                if (response.isSuccessful){
                    _list.postValue(response.body()?.listUser)
                }
            }
            override fun onFailure(call: Call<ListUserResponse>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })

    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}