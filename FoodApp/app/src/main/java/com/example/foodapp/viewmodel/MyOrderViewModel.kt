package com.example.foodapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.adapter.MyOrderAdapter
import com.example.foodapp.entity.CartFood
import com.example.foodapp.repo.FoodRepository

class MyOrderViewModel:ViewModel() {
    val foodRepo = FoodRepository()
    var cartFromList = MutableLiveData<List<CartFood>>()
    var total:Int


    init {

        loadCartFoodList()
        total = 0

        cartFromList = foodRepo.bringsFoodListCart()
    }



    fun loadCartFoodList() {
        foodRepo.getAllFoodFromCart()

    }


    fun amount() {
        var amount = 0
       for (a in cartFromList.value!!) {
           amount += a.food_price * a.cart_food_piece
           total = amount

       }
    }



    fun delete(cart_food_id:Int,userName:String) {
        foodRepo.deleteFood(cart_food_id,userName)
        loadCartFoodList()
    }



}