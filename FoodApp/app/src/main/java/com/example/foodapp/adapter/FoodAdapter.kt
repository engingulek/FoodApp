package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.entity.Food
import com.example.foodapp.databinding.FoodCardDesignBinding
import com.example.foodapp.fragment.HomePageFragmentDirections
import com.example.foodapp.viewmodel.HomePageViewModel
import com.squareup.picasso.Picasso


class FoodAdapter(var mContext:Context,var foodList: List<Food>, var viewModel: HomePageViewModel)
    : RecyclerView.Adapter<FoodAdapter.CardDesignConservative>() {

    inner class CardDesignConservative(foodCardDesignBinding : FoodCardDesignBinding)
        : RecyclerView.ViewHolder(foodCardDesignBinding.root) {
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
        val food = foodList.get(position)
        val cardDesign = holder.foodCardDesignBinding
        cardDesign.foodObject = food
        getFoodImage(food.food_image_name,cardDesign.foodImageView)
        cardDesign.foodCard.setOnClickListener {

            val pass = HomePageFragmentDirections.toDetails(food)



            Navigation.findNavController(it).navigate(pass)
        }

    }

    override fun getItemCount(): Int {
        return foodList.size
    }


    fun getFoodImage(imageName:String,imageView:ImageView) {


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
         Picasso.get().load(url).into(imageView)



    }
}