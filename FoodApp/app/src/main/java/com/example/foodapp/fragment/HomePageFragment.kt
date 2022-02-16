package com.example.foodapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.adapter.FoodHomePageCartAdapter
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.viewmodel.HomePageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomePageFragment : Fragment() {
    private lateinit var design : FragmentHomePageBinding
    private lateinit var allFoodAdapter: FoodAdapter
    private lateinit var viewModel: HomePageViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var cartListAdapter: FoodHomePageCartAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        design.fragmemntHomePage = this

        auth = Firebase.auth
        design.userName = auth.currentUser?.displayName
        var a = false


       // stepTimer()

        design.searchViewFood.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }
        })





        // All Food Adappter
        viewModel.foodList.observe(viewLifecycleOwner,{
            allFoodAdapter = FoodAdapter(requireContext(),it,viewModel)
            design.foodAdapter = allFoodAdapter
        })


        viewModel.cartFromList.observe(viewLifecycleOwner,{

            cartListAdapter =  FoodHomePageCartAdapter(requireContext(),it,viewModel)
            design.cartListFoodAdapter = cartListAdapter
            if (it.size > 0) {
                design.cartListState = true
            }

            else {
                design.cartListState = false
            }

        })







        return  design.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel:HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }


 fun applySort(sortType:Boolean) {
     viewModel.applySortFood(sortType)
 }




    fun stepTimer() {
        design.imageViewOneStep.setColorFilter(getResources().getColor(R.color.main_green));
        design.textViewOneStep.setTextColor(Color.rgb(69, 140, 124))
        design.stepContraitLayout.isGone = false
        design.constOrderNull.isGone = true

        val counter = object  : CountDownTimer(15000,1000) {
            override fun onTick(i: Long) {
                if(i.toInt()/1000  == 10 ) {
                    design.imageViewTwoStep.setColorFilter(getResources().getColor(R.color.main_green));
                    design.textViewTwoStep.setTextColor(Color.rgb(69, 140, 124))
                    design.textViewLineOne.setBackgroundColor(Color.rgb(69, 140, 124))
                }

                if (i.toInt()/1000  == 5) {
                    design.imageViewThirdStep.setColorFilter(getResources().getColor(R.color.main_green));
                    design.textViewThirdStep.setTextColor(Color.rgb(69, 140, 124))
                    design.textViewLineSecond.setBackgroundColor(Color.rgb(69, 140, 124))
                }
            }

            override fun onFinish() {
                // Bitince çalışacak
                // constrain layout gizle şuan da sipariş yok yaz
                design.stepContraitLayout.isGone = true
                design.constOrderNull.isGone = false
            }

        }

        counter.start()
    }

    fun toMyOrderAciton() {
        Navigation.findNavController(design.root).navigate(R.id.toMyOrder)
    }



    override fun onResume() {
        super.onResume()
        viewModel.loadCartFoodList()
    }




}


