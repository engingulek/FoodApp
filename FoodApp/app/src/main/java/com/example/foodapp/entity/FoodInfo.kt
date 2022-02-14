package com.example.foodapp.entity

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable

@IgnoreExtraProperties
data class FoodInfo(var foodId:String? = "",
                    var foodDetail:String? = "",
                    var foodRating:String? = "" ):Serializable {


}