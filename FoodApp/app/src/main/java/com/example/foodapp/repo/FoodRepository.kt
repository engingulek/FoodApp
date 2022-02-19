package com.example.foodapp.repo

import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.foodapp.databinding.FragmentFoodDetailPageBinding
import com.example.foodapp.entity.*
import com.example.foodapp.retrofit.ApiUtils
import com.example.foodapp.retrofit.FoodDaoInterface
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field

class FoodRepository {

    var foodList: MutableLiveData<List<Food>>
    var foodDao: FoodDaoInterface
    var cartFoodList : MutableLiveData<List<CartFood>>

    var foodInfoList : MutableLiveData<List<FoodInfo>>
    var refFoodInfo:DatabaseReference
    var refFavFood:DatabaseReference
    private lateinit var auth: FirebaseAuth

    var favFoodList : MutableLiveData<List<FavFood>>







    init {
        foodDao = ApiUtils.getFoodDaoInterface()
        foodList = MutableLiveData()
        cartFoodList = MutableLiveData()

        val db = FirebaseDatabase.getInstance()
        refFoodInfo = db.getReference("foodInfo")
        foodInfoList = MutableLiveData()
        refFavFood = db.getReference("favFood")
        favFoodList = MutableLiveData()





    }




    fun bringFoods() : MutableLiveData<List<Food>> {
        return  foodList
    }

    fun bringsFoodListCart() : MutableLiveData<List<CartFood>> {
        return  cartFoodList
    }

    fun bringsFavFood() : MutableLiveData<List<FavFood>> {
        return favFoodList
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



    fun addFoodtoCart(food_name:String, food_image_name :String, yemek_price:Int,  cart_food_piece:Int, userName:String) {
        foodDao.insertFoodToCaert(food_name, food_image_name, yemek_price,  cart_food_piece, userName).enqueue(object  : Callback<CRUDResult>{
            override fun onResponse(call: Call<CRUDResult>?, response: Response<CRUDResult>?) {

            }

            override fun onFailure(call: Call<CRUDResult>?, t: Throwable?) {

            }

        })
    }

    fun getAllFoodFromCart() {
        auth = Firebase.auth
        val email = auth.currentUser?.email
        val parts = email?.split("@")
        val userName = parts!![0]
        foodDao.allFoodFromCart(userName).enqueue(object : Callback<CartResult>{
            override fun onResponse(call: Call<CartResult>, response: Response<CartResult>) {

                cartFoodList.value = response.body().cartFoods
            }
            override fun onFailure(call: Call<CartResult>, t: Throwable) {
            }

        })
    }


    fun deleteFood(cart_food_id:Int,userName:String) {
        foodDao.deleteFoodToCart(cart_food_id,userName).enqueue(object : Callback<CRUDResult> {
            override fun onResponse(call: Call<CRUDResult>, response: Response<CRUDResult>) {
                if (cartFoodList.value != null && !cartFoodList.value!!.isEmpty()) {
                    cartFoodList!!.value!!.toMutableList().removeAt(cartFoodList.value!!.size-1)
                    cartFoodList.value = emptyList()
                }
                getAllFoodFromCart()
            }

            override fun onFailure(call: Call<CRUDResult>, t: Throwable) {

            }

        })
    }




    fun getAllFavorites() {
        refFavFood.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<FavFood>()
                for (data in snapshot.children) {
                    val food = data.getValue(FavFood::class.java)
                    if (food != null) {
                        food.key = data.key
                        list.add(food)
                    }
                }


                favFoodList.value = list

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }


    fun addMyFavorite(food:Food,design: FragmentFoodDetailPageBinding) {
        getAllFavorites()

        refFavFood.child(food.food_id.toString()).setValue(food)










    }



    fun deleteFavFood(food_id:String) {
        refFavFood.child(food_id).removeValue()
    }





}


