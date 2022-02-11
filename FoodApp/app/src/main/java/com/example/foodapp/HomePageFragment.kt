package com.example.foodapp

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.adapter.FoodAdapter
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




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)
        design.fragmemntHomePage = this

        auth = Firebase.auth
        design.userName = auth.currentUser?.displayName
        val url = auth.currentUser?.photoUrl


        stepTimer()




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
        design.imageViewOneStep.setColorFilter(getResources().getColor(R.color.black));
        design.textViewOneStep.setTextColor(Color.BLACK)

        val counter = object  : CountDownTimer(15000,1000) {
            override fun onTick(i: Long) {
                if(i.toInt()/1000  == 10 ) {
                    design.imageViewTwoStep.setColorFilter(getResources().getColor(R.color.black));
                    design.textViewTwoStep.setTextColor(Color.BLACK)
                    design.textViewLineOne.setBackgroundColor(Color.BLACK)

                }


                if (i.toInt()/1000  == 5) {
                    design.imageViewThirdStep.setColorFilter(getResources().getColor(R.color.black));
                    design.textViewThirdStep.setTextColor(Color.BLACK)
                    design.textViewLineSecond.setBackgroundColor(Color.BLACK)

                }



            }

            override fun onFinish() {
                // Bitince çalışacak
                // constrain layout gizle şuan da sipariş yok yaz
                design.stepContraitLayout.isGone = true
            }

        }

        counter.start()
    }




}


