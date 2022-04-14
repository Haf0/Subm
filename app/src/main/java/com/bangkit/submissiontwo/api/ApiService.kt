package com.bangkit.submissiontwo.api

import com.bangkit.submissiontwo.BuildConfig
import com.bangkit.submissiontwo.model.DetailUser
import com.bangkit.submissiontwo.model.ListUserResponse
import com.bangkit.submissiontwo.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("search/users")
    @Headers("Authorization: token ${BuildConfig.KEY}")
    fun getListUser(
        @Query("q") username:String
    ): Call<ListUserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token ")
    fun getDetailUserbyUsername(
        @Path("username") username: String
    ): Call<DetailUser>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

}