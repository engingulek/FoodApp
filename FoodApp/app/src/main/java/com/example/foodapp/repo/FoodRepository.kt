package com.example.foodapp.repo

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.entity.*
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDaoInterface
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodRepository {

    var foodList: MutableLiveData<List<Food>>
    var foodDao: FoodDaoInterface
    var cartFoodList : MutableLiveData<List<CartFood>>

    var foodInfoList : MutableLiveData<List<FoodInfo>>
    var refFoodInfo:DatabaseReference






    init {
        foodDao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()
        cartFoodList = MutableLiveData()

        val db = FirebaseDatabase.getInstance()
        refFoodInfo = db.getReference("foodInfo")
        foodInfoList = MutableLiveData()




    }




    fun bringFoods() : MutableLiveData<List<Food>> {
        return  foodList
    }

    fun bringsFoodListCart() : MutableLiveData<List<CartFood>> {
        return  cartFoodList
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


    fun searchFood(query:String){
        foodDao.allFood().enqueue(object :Callback<FoodResult>{
            override fun onResponse(call: Call<FoodResult>, response: Response<FoodResult>) {
                val liste = response.body().foods

                val listFiltered : List<Food> = liste.filter { it.food_name.toLowerCase().contains(query.toLowerCase()) }

                foodList.value = listFiltered
            }

            override fun onFailure(call: Call<FoodResult>, t: Throwable) {

            }

        })

    }



    fun getAllFoodFromCart() {
        foodDao.allFoodFromCart().enqueue(object : Callback<CartResult>{
            override fun onResponse(call: Call<CartResult>, response: Response<CartResult>) {
                var liste = response.body().cartFoods
                cartFoodList.value = liste

            }



            override fun onFailure(call: Call<CartResult>, t: Throwable) {

            }


        })
    }


    fun sortPrice(sortType:Boolean) {

        foodDao.allFood().enqueue(object :Callback<FoodResult>{
            override fun onResponse(call: Call<FoodResult>, response: Response<FoodResult>) {
                val liste = response.body().foods

                if (sortType == true) {
                    foodList.value = liste.sortedByDescending { it.food_price }
                }
                else {
                    foodList.value = liste.sortedBy { it.food_price }
                }
            }

            override fun onFailure(call: Call<FoodResult>, t: Throwable) {

            }

        })
    }


    fun brigsFoodInfo() : MutableLiveData<List<FoodInfo>> {
        return  foodInfoList

    }






    fun getFoodInfo(foodId:String) {
        refFoodInfo.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val liste = ArrayList<FoodInfo>()

                for (doc in snapshot.children) {
                    val info = doc.getValue(FoodInfo::class.java)
                    if (info != null) {
                        if(info.foodId == foodId) {
                            liste.add(info)

                        }


                    }

                }

                foodInfoList.value = liste
            }

            override fun onCancelled(error: DatabaseError) {}

        })

    }







}