package com.example.cryptotracker.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet.GONE
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.cryptotracker.R
import com.example.cryptotracker.adapter.MarketAdapter
import com.example.cryptotracker.apis.ApiInterface
import com.example.cryptotracker.apis.ApiUtilities
import com.example.cryptotracker.databinding.FragmentTopLossGainBinding
import com.example.cryptotracker.models.CryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Collections

class TopLossGainFragment : Fragment() {

    lateinit var binding  : FragmentTopLossGainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentTopLossGainBinding.inflate(layoutInflater)

        getMarketData()

        return binding.root
    }

    private fun getMarketData(){

        val position = requireArguments().getInt("position")

        lifecycleScope.launch(Dispatchers.IO){

            val res = ApiUtilities.getInstance().create(ApiInterface :: class.java).getMarketData()

            if(res.body() != null){

                withContext(Dispatchers.Main){
                    val dataItem = res.body()!!.data.cryptoCurrencyList

                    Collections.sort(dataItem){
                        a1,a2 -> (a2.quotes[0].percentChange24h.toInt())
                        .compareTo(a1.quotes[0].percentChange24h.toInt())
                    }

                    binding.spinKitView.visibility = GONE
                    val list = ArrayList<CryptoCurrency>()

                    if(position == 0){
                            list.clear() //to clean the old data
                            for(i in 0..9){
                                list.add(dataItem[i])
                            }

                            binding.topGainLoseRecyclerView.adapter= MarketAdapter(requireContext(), list)
                    }else{
                            list.clear() //to clean the old data
                            for(i in 0..9){
                                list.add(dataItem[dataItem.size-1 - i]) }

                            binding.topGainLoseRecyclerView.adapter= MarketAdapter(requireContext(), list)
                        }
                }

            }
        }
    }

}