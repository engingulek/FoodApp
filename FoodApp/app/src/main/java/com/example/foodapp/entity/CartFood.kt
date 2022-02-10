package com.example.foodapp.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartFood (@SerializedName("sepet_yemek_id") @Expose var cart_food_id:Int,
                @SerializedName("yemek_adi") @Expose var food_name:String,
                @SerializedName("yemek_resim_adi") @Expose var food_image_name:String,
                @SerializedName("yemek_fiyat") @Expose var food_price:Int,
                @SerializedName("yemek_siparis_adet") @Expose var cart_food_piece:Int

                ) : Serializable {



}