package com.clap.android.common.data.repository

import androidx.lifecycle.LiveData
import com.clap.android.common.data.local.db.AppDatabase
import com.clap.android.common.data.local.db.AppPrefs
import com.clap.android.common.data.local.db.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    val appDatabase: AppDatabase,
    val appPrefs: AppPrefs
) {

    val userDao = appDatabase.userDao()

    fun getUser(): LiveData<UserEntity> {
        return userDao.findById(appPrefs.userId)
    }
}