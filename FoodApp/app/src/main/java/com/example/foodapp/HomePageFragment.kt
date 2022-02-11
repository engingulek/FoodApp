package com.example.foodapp

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.foodapp.adapter.FoodAdapter
import com.example.foodapp.adapter.MorePreferedFoodAdapter
import com.example.foodapp.databinding.FragmentHomePageBinding
import com.example.foodapp.viewmodel.HomePageViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomePageFragment : Fragment() , SearchView.OnQueryTextListener  {
    private lateinit var design : FragmentHomePageBinding
    private lateinit var allFoodAdapter: FoodAdapter
    private lateinit var viewModel: HomePageViewModel
    private  lateinit var morePreferedFoodAdapter: MorePreferedFoodAdapter
    private lateinit var auth: FirebaseAuth




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_home_page, container, false)
        design.fragmemntHomePage = this
        (activity as AppCompatActivity).setSupportActionBar(design.toolbarHomePage)
        auth = Firebase.auth
        design.userName = auth.currentUser?.displayName




        // All Food Adappter
        viewModel.foodList.observe(viewLifecycleOwner,{
            allFoodAdapter = FoodAdapter(requireContext(),it,viewModel)
            design.foodAdapter = allFoodAdapter
        })



        // All More Prefered Adapter
        viewModel.morePreferedFoodList.observe(viewLifecycleOwner,{
            morePreferedFoodAdapter = MorePreferedFoodAdapter(requireContext(),it,viewModel)
            design.morePreferedFoodAdapter = morePreferedFoodAdapter
        })







        return  design.root






    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel:HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search,menu)
        val item = menu.findItem(R.id.action_search)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

 fun applySort(sortType:Boolean) {
     viewModel.applySortFood(sortType)
 }

    override fun onQueryTextSubmit(query: String): Boolean {//Arama iconu tıklanıldığında çalışır.
viewModel.search(query)

        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {//Her harf girdikçe ve sildikçe çalışır.
        viewModel.search(newText)
        return true
    }


}