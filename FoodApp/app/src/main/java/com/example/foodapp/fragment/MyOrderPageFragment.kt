package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMyOrderPageBinding


class MyOrderPageFragment : Fragment() {

    private lateinit var design: FragmentMyOrderPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_my_order_page, container, false)
        design.fragmentMyOrderPage = this

        return design.root
    }


}