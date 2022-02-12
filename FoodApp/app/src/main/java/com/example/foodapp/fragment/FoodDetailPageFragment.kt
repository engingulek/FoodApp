package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foodapp.R
import com.example.foodapp.databinding.FoodCardDesignBinding
import com.example.foodapp.databinding.FragmentFoodDetailPageBinding


class FoodDetailPageFragment : Fragment() {
    private lateinit var design: FragmentFoodDetailPageBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_food_detail_page, container, false)
        design.fragmentDetailPage = this


        return design.root
    }


}