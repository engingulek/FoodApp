package com.example.foodapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.MyOrderFoodCartDesignBinding
import com.example.foodapp.entity.CartFood
import com.example.foodapp.viewmodel.HomePageViewModel
import com.example.foodapp.viewmodel.MyOrderViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

class MyOrderAdapter(var mContext: Context, var foodCartList: List<CartFood>,
                     var viewModel: MyOrderViewModel)
    : RecyclerView.Adapter<MyOrderAdapter.CardDesignConservative>(){
    private lateinit var auth: FirebaseAuth

        inner class CardDesignConservative(myOrderFoodCartDesignBinding: MyOrderFoodCartDesignBinding)
            :RecyclerView.ViewHolder(myOrderFoodCartDesignBinding.root) {
                var myOrderFoodCartDesignBinding : MyOrderFoodCartDesignBinding
                init {
                    this.myOrderFoodCartDesignBinding = myOrderFoodCartDesignBinding
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignConservative {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = MyOrderFoodCartDesignBinding.inflate(layoutInflater,parent,false)
        return  CardDesignConservative(design)
    }

    override fun onBindViewHolder(holder: CardDesignConservative, position: Int) {
        val food = foodCartList.get(position)
        val cardDesign = holder.myOrderFoodCartDesignBinding
        cardDesign.foodCartObject = food
        getFoodImage(food.food_image_name,cardDesign.orderImage)
        auth = Firebase.auth
        val email = auth.currentUser?.email
        val parts = email?.split("@")
        val userName = parts!![0]
        cardDesign.bttnRemove.setOnClickListener {
            viewModel.delete(food.cart_food_id,userName)
            viewModel.loadCartFoodList()





        }



    }

    override fun getItemCount(): Int {
        return  foodCartList.size
    }


    fun getFoodImage(imageName:String,imageView: ImageView) {


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get().load(url).into(imageView)



    }




}