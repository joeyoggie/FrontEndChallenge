package com.clap.android.common.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.clap.android.BuildConfig
import com.clap.android.common.data.local.db.dao.UserDao
import com.clap.android.common.data.local.db.entities.UserEntity
import com.clap.android.features.currency.data.local.db.dao.CurrencyDao
import com.clap.android.features.currency.data.local.db.entities.CurrencyEntity

@Database(entities = arrayOf(
    UserEntity::class,
    CurrencyEntity::class),
    version = BuildConfig.VERSION_CODE,
    exportSchema = false)
//TODO @TypeConverters(...)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun currencyDao(): CurrencyDao


    companion object {
        private val DB_NAME = "Clap.db"

        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = appDatabase
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
                appDatabase = instance
                return instance
            }
        }
    }
}