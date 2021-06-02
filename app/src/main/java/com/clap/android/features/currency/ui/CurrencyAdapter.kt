package com.clap.android.features.currency.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.clap.android.R

class CurrencyAdapter(private val items: List<CurrencyItem>, private val currencySelectedListener: CurrencySelectedListener): RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_currency, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = items[position]
        viewHolder.tvName.text = item.name
        viewHolder.tvRate.text = item.rate
        if(item.imageResourceId != -1) {
            viewHolder.ivFlag.setImageResource(item.imageResourceId)
        }

        viewHolder.lyCurrency.setOnClickListener {
            currencySelectedListener.onCurrencySelected(item.name)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lyCurrency: ConstraintLayout
        val ivFlag: ImageView
        val tvRate: TextView
        val tvName: TextView

        init {
            lyCurrency = view.findViewById(R.id.lyCurrency)
            ivFlag = view.findViewById(R.id.ivFlag)
            tvRate = view.findViewById(R.id.tvRate)
            tvName = view.findViewById(R.id.tvName)
        }
    }

}