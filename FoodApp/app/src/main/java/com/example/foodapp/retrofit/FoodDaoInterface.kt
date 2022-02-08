package com.example.foodapp.retrofit

import com.example.foodapp.entity.FoodResult
import retrofit2.Call
import retrofit2.http.GET

interface FoodDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFood(): Call<FoodResult>

}