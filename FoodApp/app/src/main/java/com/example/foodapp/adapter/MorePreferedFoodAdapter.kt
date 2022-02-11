package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.AllFoodPrefedCardDesignBinding
import com.example.foodapp.entity.CartFood
import com.example.foodapp.viewmodel.HomePageViewModel
import com.squareup.picasso.Picasso

class MorePreferedFoodAdapter (var mContext:Context,var morePreferesFoodList:List<CartFood>,var viewModel: HomePageViewModel)
    : RecyclerView.Adapter<MorePreferedFoodAdapter.MorePreferedCardDesignConservative>(){

        inner class MorePreferedCardDesignConservative(morePrefedCardDesignBinding: AllFoodPrefedCardDesignBinding)
            :RecyclerView.ViewHolder(morePrefedCardDesignBinding.root) {
                var morePrefedCardDesignBinding : AllFoodPrefedCardDesignBinding
                init {
                    this.morePrefedCardDesignBinding = morePrefedCardDesignBinding
                }
            }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MorePreferedCardDesignConservative {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = AllFoodPrefedCardDesignBinding.inflate(layoutInflater,parent,false)
        return  MorePreferedCardDesignConservative(design)
    }

    override fun onBindViewHolder(holder: MorePreferedCardDesignConservative, position: Int) {
       val food = morePreferesFoodList.get(position)
        val cardDesign = holder.morePrefedCardDesignBinding
        cardDesign.foodObject = food
        getFoodImage(food.food_image_name,cardDesign.preferedFoodImageView)



    }

    override fun getItemCount(): Int {
        return morePreferesFoodList.size
    }

    fun getFoodImage(imageName:String,imageView: ImageView) {


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get().load(url).into(imageView)



    }


}