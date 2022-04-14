package com.bangkit.submissiontwo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDatabase::class], version = 1)
abstract class UsersRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UsersRoomDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UsersRoomDatabase{
            if (INSTANCE == null){
                synchronized(UsersRoomDatabase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UsersRoomDatabase::class.java,"users")
                        .build()
                }
            }
            return INSTANCE as UsersRoomDatabase
        }
    }
}