package com.example.foodapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.databinding.HomepageCartListDesignBinding
import com.example.foodapp.databinding.MyOrderFoodCartDesignBinding
import com.example.foodapp.entity.CartFood
import com.example.foodapp.viewmodel.HomePageViewModel

class FoodHomePageCartAdapter(
    var mContext: Context, var foodCartList: List<CartFood>,
    var viewModel: HomePageViewModel,


)
    : RecyclerView.Adapter<FoodHomePageCartAdapter.CardDesignConservative>(){



        inner class CardDesignConservative(homepageCartListDesignBinding: HomepageCartListDesignBinding)
            :RecyclerView.ViewHolder(homepageCartListDesignBinding.root) {
                var homepageCartListDesignBinding : HomepageCartListDesignBinding
                init {
                    this.homepageCartListDesignBinding = homepageCartListDesignBinding

                }
            }










    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignConservative {

        val layoutInflater = LayoutInflater.from(mContext)
        val design = HomepageCartListDesignBinding.inflate(layoutInflater,parent,false)
   return  CardDesignConservative(design)
    }

    override fun onBindViewHolder(holder: CardDesignConservative, position: Int) {
        val food = foodCartList.get(position)


        val cardDesing = holder.homepageCartListDesignBinding
        cardDesing.foodCartObject = food
        Log.e("Sipariş Adeti","${food.cart_food_piece}")

    }

    override fun getItemCount(): Int {

        return  foodCartList.size

    }
}