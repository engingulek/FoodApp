package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import com.example.foodapp.R
import com.example.foodapp.adapter.MyFavoriteAdapter
import com.example.foodapp.databinding.FragmentMyFavoritesBinding
import com.example.foodapp.viewmodel.MyFavoriteViewModel


class MyFavoritesFragment : Fragment() {
    private lateinit var design : FragmentMyFavoritesBinding
    private lateinit var viewModel : MyFavoriteViewModel
    private lateinit var adapter: MyFavoriteAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_my_favorites, container, false)
        design.fragmentMyFavorite = this

        viewModel.myFavoriteList.observe(viewLifecycleOwner,{
            adapter = MyFavoriteAdapter(requireContext(),it,viewModel)
            design.adapter = adapter

        })




        return design.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:MyFavoriteViewModel by viewModels()
        viewModel = tempViewModel




    }


}