package com.example.foodapp.retrofit

import android.media.Image
import android.widget.ImageView
import com.example.foodapp.entity.CRUDResult
import com.example.foodapp.entity.CartResult
import com.example.foodapp.entity.FoodResult
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDaoInterface {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFood(): Call<FoodResult>


    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun allFoodFromCart(@Field("kullanici_adi") userName:String) : Call<CartResult>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun insertFoodToCaert(@Field("yemek_adi") food_name:String,
                          @Field("yemek_resim_adi") food_image_name :String,
                          @Field("yemek_fiyat") yemek_price:Int,
                          @Field("yemek_siparis_adet") cart_food_piece:Int,
                          @Field("kullanici_adi") userName:String ) : Call<CRUDResult>


    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodToCart(@Field("sepet_yemek_id") cart_food_id:Int, @Field("kullanici_adi") userName:String ) : Call<CRUDResult>






}