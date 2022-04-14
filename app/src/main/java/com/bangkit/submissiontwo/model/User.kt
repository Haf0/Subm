package com.bangkit.submissiontwo.model

import com.google.gson.annotations.SerializedName

data class User(
    @field:SerializedName("login")
    val username: String,
    @field:SerializedName("avatar_url")
    val avatar: String
)
