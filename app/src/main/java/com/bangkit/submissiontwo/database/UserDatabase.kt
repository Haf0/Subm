package com.bangkit.submissiontwo.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "users")
@Parcelize
data class UserDatabase(
    @PrimaryKey
    @ColumnInfo(name= "username")
    val username: String,
    @ColumnInfo(name= "avatar")
    val avatar: String
): Parcelable
