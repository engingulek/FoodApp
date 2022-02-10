package com.example.foodapp.repo

import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.CartFood
import com.example.foodapp.entity.CartResult
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
    var morePreferedFoodList : MutableLiveData<List<CartFood>>


    init {
        foodDao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()
        morePreferedFoodList = MutableLiveData()

    }


    fun bringFoods() : MutableLiveData<List<Food>> {
        return  foodList
    }

    fun bringsMorePreferedFood() : MutableLiveData<List<CartFood>> {
        return  morePreferedFoodList
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




    fun getAllMorePreferedFood() {
        foodDao.allFoodFromCart().enqueue(object : Callback<CartResult>{
            override fun onResponse(call: Call<CartResult>, response: Response<CartResult>) {
                var liste = response.body().cartFoods
                var cartFoodList = HashSet<CartFood>()
                for (food in liste) {
                    cartFoodList.add(food)
                }

                morePreferedFoodList.value =  cartFoodList.sortedByDescending { it.cart_food_piece }.take(5)
            }



            override fun onFailure(call: Call<CartResult>, t: Throwable) {

            }


        })
    }




}