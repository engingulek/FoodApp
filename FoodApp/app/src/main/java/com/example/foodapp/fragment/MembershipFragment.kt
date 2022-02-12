package com.example.foodapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentMembershipBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MembershipFragment : Fragment() {


    private lateinit var design: FragmentMembershipBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        design = DataBindingUtil.inflate(inflater, R.layout.fragment_membership, container, false)
       design.fragmentMemebershipObject = this

        auth = Firebase.auth
        cardViewStatus()

        return design.root
    }



    fun bttnSingInUpAction() {
        // Gerekli olan cardView kontrolü yapılmaktadır.
        if (design.cardViewSingIn.visibility == View.VISIBLE) {


            if (design.edtEmailAdres.text.toString() == "" ||
                design.edTPassword.text.toString() == "") {
                alertMessage(R.string.alert_message_null)
            }

            else {
                // Kullanıcı girişi yapılamaktadır
                auth.signInWithEmailAndPassword(
                    design.edtEmailAdres.text.toString(),
                    design.edTPassword.text.toString()
                )
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information

                            Navigation.findNavController(design.root).navigate(R.id.toHomePage)

                        } else {
                            alertMessage(R.string.alert_message)
                        }
                    }
            }

        } else {

            if(design.edtEmailSingUp.text.toString() == "" ||
                design.edtPasswordSingUp.text.toString()== "" ||
                design.edtName.text.toString() == "") {
                alertMessage(R.string.alert_message_null)

            }

            else {
                // Hesap oluşturma işlemi yapılmakdır.
                auth.createUserWithEmailAndPassword(
                    design.edtEmailSingUp.text.toString(),
                    design.edtPasswordSingUp.text.toString()
                )
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val profileUpdates = UserProfileChangeRequest.Builder().apply {
                                displayName = design.edtName.text.toString()
                            }.build()
                            user?.updateProfile(profileUpdates)?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Navigation.findNavController(design.root).navigate(R.id.toHomePage)

                                }
                            }


                        } else {
                            // If sign in fails, display a message to the user.

                            // Error code larının alımı bulunursa değiştirlecek
                            if ("${task.exception}" == "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.") {
                                alertMessage(R.string.alert_message_same_accomt)

                            }

                            if("${task.exception}" == "com.google.firebase.auth.FirebaseAuthWeakPasswordException: The given password is invalid. [ Password should be at least 6 characters ]") {
                                alertMessage(R.string.alert_message_password_char)
                            }

                            if ("${task.exception}" == "com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted.") {
                                alertMessage(R.string.alert_message_email_badly_format)
                            }

                            Log.e("Hata","${task.exception}")


                        }
                    }
            }

        }

    }





    // Daha önceden giriş yapılıp yapılmadığı kontrol edilmektedir.
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null ) {
            Navigation.findNavController(design.root).navigate(R.id.toHomePage)
        }

    }




    // CardViewlerin görünürlük durumunu ayarlama
    fun cardViewStatus() {
        design.cardViewSingUp.isVisible = false

        design.bttnSingInView.setOnClickListener {
            design.cardViewSingIn.isVisible = true
            design.cardViewSingUp.isVisible = false
            design.bttnAction.text = "Giriş Yap"
            Log.e("Visible singIna durumu","${design.cardViewSingIn.visibility}")
            Log.e("Visible singuPa durumu","${design.cardViewSingUp.visibility}")
        }


        design.bttnSingUpView.setOnClickListener {
            design.cardViewSingIn.isVisible = false
            design.cardViewSingUp.isVisible = true
            design.bttnAction.text = "Kayıt Ol"
            Log.e("Visible singIn durumu","${design.cardViewSingIn.visibility}")
            Log.e("Visible singUp durumu","${design.cardViewSingUp.visibility}")
        }


    }




    fun alertMessage(a:Int){

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.error))
            .setMessage(resources.getString(a))

            .setPositiveButton(resources.getString(R.string.close)) { dialog, which ->
                // Respond to positive button press
            }
            .show()
    }







}

