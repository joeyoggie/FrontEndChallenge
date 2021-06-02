package com.clap.android.features.currency.data.mapper

import com.clap.android.features.currency.data.local.db.entities.CurrencyEntity
import com.clap.android.features.currency.data.remote.entities.GetRatesResponse
import com.clap.android.features.currency.ui.CurrencyItem

fun GetRatesResponse.CurrencyRemoteEntity.toCurrencyEntity(locale: String, imageResourceId: Int) = CurrencyEntity(
    name =  this.name ?: "-",
    rate = this.rate,
    imageResourceId = imageResourceId
)

fun CurrencyEntity.toCurrencyItem(locale: String) = CurrencyItem(
    name =  this.name ?: "-", //probably use locale here if the entity contains multiple localized fields
    rate = this.rate.toString(),
    imageResourceId = this.imageResourceId ?: -1
)