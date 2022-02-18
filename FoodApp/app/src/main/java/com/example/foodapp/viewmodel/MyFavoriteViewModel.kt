package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.FavFood
import com.example.foodapp.repo.FoodRepository

class MyFavoriteViewModel : ViewModel() {

    var myFavoriteList : MutableLiveData<List<FavFood>>
    val frepo = FoodRepository()


    init {
        loadFavFood()
        myFavoriteList = frepo.bringsFavFood()

    }



    fun loadFavFood() {
        frepo.getAllFavorites()
    }


    fun addFoodToCart(food_name:String, food_image_name :String, food_price:Int,  cart_food_piece:Int, userName:String) {

        frepo.addFoodtoCart(food_name, food_image_name, food_price,  cart_food_piece, userName)

    }

    fun favDelete(food_id:String){
        frepo.deleteFavFood(food_id)
    }



}