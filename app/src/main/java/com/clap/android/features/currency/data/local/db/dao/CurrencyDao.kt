package com.clap.android.features.currency.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.clap.android.features.currency.data.local.db.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currencies")
    fun getAll(): LiveData<List<CurrencyEntity>>

    @Query("SELECT * FROM currencies WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): LiveData<CurrencyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg currencies: CurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(currencies: List<CurrencyEntity>)

    @Delete
    fun deleteAll(currency: CurrencyEntity)
}