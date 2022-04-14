package com.bangkit.submissiontwo.model

import com.google.gson.annotations.SerializedName

data class DetailUser(
    @field:SerializedName("login")
    val username: String,
    @field:SerializedName("avatar_url")
    val avatar: String,
    val name: String,
    val company: String?,
    val blog: String?,
    val location: String?,
    val followers: String,
    val following: String
)
