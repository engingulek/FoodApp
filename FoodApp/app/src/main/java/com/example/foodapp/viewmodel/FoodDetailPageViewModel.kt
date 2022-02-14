package com.example.foodapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.entity.CartFood
import com.example.foodapp.entity.FoodInfo
import com.example.foodapp.repo.FoodRepository
import com.google.firebase.firestore.QuerySnapshot

class FoodDetailPageViewModel : ViewModel() {
var foodInfoList = MutableLiveData<List<FoodInfo>>()
    val frepo = FoodRepository()




    init {

        foodInfoList = frepo.brigsFoodInfo()
    }




    fun foodInfo(id:String) {
        frepo.getFoodInfo(id)
    }






}