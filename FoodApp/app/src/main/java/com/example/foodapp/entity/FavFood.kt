package com.example.foodapp.entity

import java.io.Serializable

data class FavFood(var food_id:Int? = 0,
                   var food_name:String? = "",
                var food_image_name:String? = "",
                  var food_price:Int? = 0, var key:String? = ""):Serializable {

}