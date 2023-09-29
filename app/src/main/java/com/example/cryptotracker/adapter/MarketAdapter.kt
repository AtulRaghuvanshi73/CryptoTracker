package com.example.cryptotracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptotracker.R
import com.example.cryptotracker.databinding.CurrencyItemLayoutBinding
import com.example.cryptotracker.models.CryptoCurrency

class MarketAdapter(var context: Context, var list : List<CryptoCurrency>) : RecyclerView.Adapter<MarketAdapter.MarketViewHolder>() {

    inner class MarketViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var binding  = CurrencyItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketViewHolder {
        return MarketViewHolder(LayoutInflater.from(context).inflate(R.layout.currency_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: MarketViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}