package com.example.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodResult(@SerializedName("yemekler") @Expose var foods:List<Food>,
                 @SerializedName("success") @Expose  var success:Int) {

}