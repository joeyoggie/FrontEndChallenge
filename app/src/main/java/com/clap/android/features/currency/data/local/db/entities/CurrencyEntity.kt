package com.clap.android.features.currency.data.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyEntity(
    @PrimaryKey
    val name: String,
    val rate: Double?,
    val imageResourceId: Int?
)