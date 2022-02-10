package com.example.foodapp.repo

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.Food
import com.example.foodapp.entity.FoodResult
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDaoInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository {

    var foodList: MutableLiveData<List<Food>>
    var foodDao: FoodDaoInterface


    init {
        foodDao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()

    }


    fun bringFoods() : MutableLiveData<List<Food>> {
        return  foodList
    }


    fun getAllFood(){
        foodDao.allFood().enqueue(object :Callback<FoodResult>{
            override fun onResponse(call: Call<FoodResult>, response: Response<FoodResult>) {
                val liste = response.body().foods
                foodList.value = liste

            }

            override fun onFailure(call: Call<FoodResult>, t: Throwable) {

            }

        })

    }




}