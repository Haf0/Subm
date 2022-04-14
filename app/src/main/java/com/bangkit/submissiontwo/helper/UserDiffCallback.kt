package com.bangkit.submissiontwo.helper

import androidx.recyclerview.widget.DiffUtil
import com.bangkit.submissiontwo.database.UserDatabase

class UserDiffCallback(private val mOldUserList: List<UserDatabase>, private val mNewUserList: List<UserDatabase>) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return mOldUserList.size
    }

    override fun getNewListSize(): Int {
        return mNewUserList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].username == mNewUserList[newItemPosition].username
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldUserList[oldItemPosition].username == mNewUserList[newItemPosition].username && mOldUserList[oldItemPosition].avatar == mNewUserList[newItemPosition].avatar
    }
}