package com.bangkit.submissiontwo.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.bangkit.submissiontwo.database.UserDao
import com.bangkit.submissiontwo.database.UserDatabase
import com.bangkit.submissiontwo.database.UsersRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class UsersRepository(application: Application) {
    private val mUsersDao : UserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = UsersRoomDatabase.getDatabase(application)
        mUsersDao = db.userDao()
    }

    fun getAllUsers(): LiveData<List<UserDatabase>> = mUsersDao.getAllUsers()

    fun insert(users: UserDatabase){
        executorService.execute { mUsersDao.insert(users) }
    }
    fun delete(users: UserDatabase){
        executorService.execute { mUsersDao.delete(users) }
    }
    fun deleteAllUsers(){
        executorService.execute { mUsersDao.deleteAllusers() }
    }
}