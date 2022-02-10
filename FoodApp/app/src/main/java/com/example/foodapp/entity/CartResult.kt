package com.example.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartResult(@SerializedName("sepet_yemekler") @Expose var cartFoods:List<CartFood>,
                 @SerializedName("success") @Expose var success:Int) {

}
