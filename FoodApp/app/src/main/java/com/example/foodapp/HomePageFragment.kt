package com.example.foodapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.viewmodel.HomePageViewModel


class HomePageFragment : Fragment() {
    private lateinit var design : FragmentHomePageBinding
    private lateinit var adapter: FoodAdapter
    private lateinit var viewModel: HomePageViewModel




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)


        viewModel.foodList.observe(viewLifecycleOwner,{
            adapter = FoodAdapter(requireContext(),it,viewModel)
            design.foodAdapter = adapter
        })




        return  design.root





    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }


}