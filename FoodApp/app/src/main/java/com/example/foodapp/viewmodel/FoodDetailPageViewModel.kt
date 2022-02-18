package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.CartFood
import com.example.foodapp.entity.Food
import com.example.foodapp.entity.FoodInfo
import com.example.foodapp.repo.FoodRepository
import com.google.firebase.firestore.QuerySnapshot

class FoodDetailPageViewModel : ViewModel() {
var foodInfoList = MutableLiveData<List<FoodInfo>>()
    val frepo = FoodRepository()





    init {

        foodInfoList = frepo.brigsFoodInfo()

    }


    fun addFavoriteFood(food: Food) {
        frepo.addMyFavorite(food)

    }

    fun foodInfo(id:String) {
        frepo.getFoodInfo(id)
    }



    fun addFoodToCart(food_name:String, food_image_name :String, food_price:Int,  cart_food_piece:Int, userName:String) {
        frepo.addFoodtoCart(food_name, food_image_name, food_price,  cart_food_piece, userName)

    }





}