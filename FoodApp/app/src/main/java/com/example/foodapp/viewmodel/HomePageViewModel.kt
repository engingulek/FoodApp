package com.example.foodapp.viewmodel

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.Food
import com.example.foodapp.repo.FoodRepository

class HomePageViewModel :ViewModel() {
    var foodList = MutableLiveData<List<Food>>()
    val foodRepo = FoodRepository()

    init {
        loadFoods()
     foodList =  foodRepo.bringFoods()

    }

    fun loadFoods() {
        foodRepo.getAllFood()
    }







}