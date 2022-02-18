package com.example.foodapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.databinding.MyFavCardDesignBinding
import com.squareup.picasso.Picasso


import com.example.foodapp.entity.FavFood
import com.example.foodapp.viewmodel.MyFavoriteViewModel
import com.google.android.material.snackbar.Snackbar

class MyFavoriteAdapter (var mContext:Context,
                         var favFoodList: List<FavFood>,
                         var viewModel:MyFavoriteViewModel)
    :RecyclerView.Adapter<MyFavoriteAdapter.CardDesignConservative>() {

      inner class CardDesignConservative(myFavCardDesignBinding: MyFavCardDesignBinding)
          :RecyclerView.ViewHolder(myFavCardDesignBinding.root) {
              var myFavCardDesignBinding: MyFavCardDesignBinding
              init {
                  this.myFavCardDesignBinding = myFavCardDesignBinding
              }


          }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignConservative {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = MyFavCardDesignBinding.inflate(layoutInflater,parent,false)
        return CardDesignConservative(design)
    }

    override fun onBindViewHolder(holder: CardDesignConservative, position: Int) {
        val favFood = favFoodList.get(position)
        val cardDesign = holder.myFavCardDesignBinding
        cardDesign.foodFavObject = favFood
        cardDesign.piece = "1"
        getFoodImage(favFood.food_image_name!!,cardDesign.favImage)


        cardDesign.bttnPricePlusFav.setOnClickListener {
            val a =  cardDesign.textViewPriceFav.text.toString().toInt() + 1
            cardDesign.textViewPriceFav.text = a.toString()
        }

        cardDesign.ivDelete.setOnClickListener {
            Snackbar.make(it,"Silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("EVET"){
                    viewModel.favDelete(favFood.key!!)
                }.show()
        }

        cardDesign.bttnDecraseFav.setOnClickListener {
            if(cardDesign.textViewPriceFav.text.toString() != "1") {
                val a =  cardDesign.textViewPriceFav.text.toString().toInt() - 1
                cardDesign.textViewPriceFav.text = a.toString()
            }


        }



        cardDesign.bttnAddCartFav.setOnClickListener {

            val piece = cardDesign.textViewPriceFav.text.toString().toInt()
            viewModel.addFoodToCart(favFood.food_name!!,favFood.food_image_name!!,favFood.food_price!!,piece,"denemeUserName")


        }

    }

    override fun getItemCount(): Int {
        return favFoodList.size

    }


    fun getFoodImage(imageName:String,imageView: ImageView) {


        val url = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
        Picasso.get().load(url).into(imageView)



    }


}