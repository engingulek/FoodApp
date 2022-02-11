package com.example.foodapp.viewmodel

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
    var morePreferedFoodList = MutableLiveData<List<CartFood>>()

    init {
        loadFoods()
        loadMorePreferedFoods()
     foodList =  foodRepo.bringFoods()
        morePreferedFoodList = foodRepo.bringsMorePreferedFood()




    }

    fun loadFoods() {
        foodRepo.getAllFood()
    }

    fun loadMorePreferedFoods() {
        foodRepo.getAllMorePreferedFood()

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







}