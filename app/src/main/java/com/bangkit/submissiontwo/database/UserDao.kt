package com.bangkit.submissiontwo.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: UserDatabase)

    @Delete
    fun delete(user: UserDatabase)

    @Query("SELECT * from users ORDER BY username ASC")
    fun getAllUsers(): LiveData<List<UserDatabase>>

    @Query("DELETE FROM users")
    fun deleteAllusers()
}