package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.adapter.MyOrderAdapter
import com.example.foodapp.databinding.FragmentMyOrderPageBinding
import com.example.foodapp.viewmodel.MyOrderViewModel


class MyOrderPageFragment : Fragment() {

    private lateinit var design: FragmentMyOrderPageBinding
    private lateinit var viewModel : MyOrderViewModel
    private lateinit var cartListAdapter: MyOrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_my_order_page, container, false)
        design.fragmentMyOrderPage = this
        design.totalAmount = "0"


        viewModel.cartFromList.observe(viewLifecycleOwner,{
            cartListAdapter = MyOrderAdapter(requireContext(),it, viewModel)
            design.adapter = cartListAdapter
            design.piece = it.count().toString()
            viewModel.amount()
            design.totalAmount = viewModel.total.toString()
            if (it.count() == 0) {
                design.totalAmount = "0"
            }



        })




        return design.root
    }


    fun toHomePage() {
        val pass: NavDirections
      if(design.bttnPay.text.toString() == "Pay")  {
           pass = MyOrderPageFragmentDirections.fromMyOrdertoHomePage(true)
          viewModel.cartFromList.observe(viewLifecycleOwner,{
              for(food in it) {
                  viewModel.delete(food.cart_food_id,"denemeUserName")
              }
          })
      }
        else {
           pass = MyOrderPageFragmentDirections.fromMyOrdertoHomePage(false)
       }

        Navigation.findNavController(design.root).navigate(pass)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: MyOrderViewModel by viewModels()
        viewModel = tempViewModel
    }




}