package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.entity.Food
import com.example.foodapp.databinding.FoodCardDesignBinding
import com.example.foodapp.viewmodel.HomePageViewModel

class FoodAdapter(var mContext:Context,var foodList: List<Food>, var viewModel: HomePageViewModel) : RecyclerView.Adapter<FoodAdapter.CardDesignConservative>() {

    inner class CardDesignConservative(foodCardDesignBinding : FoodCardDesignBinding)  : RecyclerView.ViewHolder(foodCardDesignBinding.root) {
        var foodCardDesignBinding : FoodCardDesignBinding
         init {
             this.foodCardDesignBinding = foodCardDesignBinding
         }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignConservative {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = FoodCardDesignBinding.inflate(layoutInflater,parent,false)
        return  CardDesignConservative(design)
    }

    override fun onBindViewHolder(holder: CardDesignConservative, position: Int) {
        // tıklanma işlemi
        val food = foodList.get(position)
        val cardDesign = holder.foodCardDesignBinding
        cardDesign.foodObject = food

    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}