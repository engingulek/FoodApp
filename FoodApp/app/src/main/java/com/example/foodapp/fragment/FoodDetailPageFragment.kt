package com.example.foodapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentFoodDetailPageBinding
import com.example.foodapp.entity.Food
import com.example.foodapp.viewmodel.FoodDetailPageViewModel
import com.example.foodapp.viewmodel.MyFavoriteViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


class FoodDetailPageFragment : Fragment() {
    private lateinit var design: FragmentFoodDetailPageBinding
    private lateinit var viewModel: FoodDetailPageViewModel
    private lateinit var myFavViewModel:MyFavoriteViewModel

    private lateinit var auth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail_page, container, false)
        design.fragmentDetailPage = this

        val bundle : FoodDetailPageFragmentArgs by navArgs()
        val getFood = bundle.food
        design.foodObject = getFood



        viewModel.foodInfoList.observe(viewLifecycleOwner,{

                Log.e("Adet","${it.count()}")
           design.foodInfoObject = it.get(0)

        })

        viewModel.foodInfo(getFood.food_id.toString())

getFoodImage(getFood.food_image_name)






        return design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel:FoodDetailPageViewModel by viewModels()
        viewModel = tempViewModel

        val favTempViewModel : MyFavoriteViewModel by viewModels()
        myFavViewModel = favTempViewModel


    }


    fun getFoodImage(imageName:String) {
        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get().load(url).into(design.imageViewFood)
    }

    fun bttnPlusAction() {
        val a =  design.textViewPrice.text.toString().toInt() + 1
        design.textViewPrice.text = a.toString()

    }


    fun bttnDecraseAction() {
        if(design.textViewPrice.text.toString() != "1") {
            val a =  design.textViewPrice.text.toString().toInt() - 1
            design.textViewPrice.text = a.toString()

        }

    }

    fun bttnAddCart(food:Food) {
        auth = Firebase.auth
        val email = auth.currentUser?.email
        val parts = email?.split("@")
        val userName = parts!![0]
        val piece = design.textViewPrice.text.toString().toInt()
        viewModel.addFoodToCart(food.food_name,food.food_image_name,food.food_price,piece,userName)

    }


    fun addFavorite(food: Food) {

        viewModel.addFavoriteFood(food,design)


    }






}