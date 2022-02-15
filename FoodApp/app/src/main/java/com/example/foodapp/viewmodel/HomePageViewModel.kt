package com.example.foodapp.viewmodel

import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.entity.CartFood
import com.example.foodapp.entity.Food
import com.example.foodapp.repo.FoodRepository

class HomePageViewModel :ViewModel() {
    var foodList = MutableLiveData<List<Food>>()
    val foodRepo = FoodRepository()
    var cartFromList = MutableLiveData<List<CartFood>>()




    init {
        loadFoods()
        loadCartFoodList()
     foodList =  foodRepo.bringFoods()
        cartFromList = foodRepo.bringsFoodListCart()
    }

    fun loadFoods() {
        foodRepo.getAllFood()
    }

    fun loadCartFoodList() {
        foodRepo.getAllFoodFromCart()

    }

    fun applySortFood(sortType:Boolean) {
        foodRepo.sortPrice(sortType)

    }

    fun search(query:String) {
        if (query == "") {
            loadFoods()
        }
        else {
            foodRepo.searchFood(query)
        }

    }



    fun addFoodToCart(food_name:String, food_image_name :String, food_price:Int,  cart_food_piece:Int, userName:String) {
        foodRepo.addFoodtoCart(food_name, food_image_name, food_price,  cart_food_piece, userName)

    }







}