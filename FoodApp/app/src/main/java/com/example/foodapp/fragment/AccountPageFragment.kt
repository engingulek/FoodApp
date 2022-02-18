package com.example.foodapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentAccountPageBinding
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class AccountPageFragment : Fragment() {
    private lateinit var design : FragmentAccountPageBinding
    private lateinit var auth:FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View? {
        design = DataBindingUtil.inflate(inflater,R.layout.fragment_account_page, container, false)

        auth = Firebase.auth
        design.fragmentAccountPage = this
        design.textViewAccountUserName.text = auth.currentUser?.displayName

        return design.root
    }



    fun singOutAcciton() {
        Firebase.auth.signOut()
        Navigation.findNavController(design.root).navigate(R.id.toMemberShip)

    }

    fun toMyFavorites() {
        Navigation.findNavController(design.root).navigate(R.id.toMyFavorites)
    }

}