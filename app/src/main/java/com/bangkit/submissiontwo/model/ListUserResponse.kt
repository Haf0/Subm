package com.bangkit.submissiontwo.model

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @field:SerializedName("items")
    val listUser: List<User>
)
