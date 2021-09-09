package com.example.foodresp.fragment.recipe

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.foodresp.R
import com.example.foodresp.data.model.Result
import com.google.android.material.chip.Chip

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageWithUrl")
    fun loadUImageWithUrl(imageView: ImageView,url:String){
        //将url对应的图片下载下来 显示到imageView上
        //Glide
        Glide.with(imageView.context).load(url).into(imageView)
    }

    @JvmStatic
    @BindingAdapter("loadIngredientImageWithName")
    fun loadIngredientImageWithName(imageView: ImageView,name:String){
        //将url对应的图片下载下来 显示到imageView上
        //Glide
        val imageBaseUrl = "https://spoonacular.com/cdn/ingredients_250x250/"
        Glide.with(imageView.context)
                .load(imageBaseUrl+name)
                //设置默认图片 未加载完成时显示的图片
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
    }


    @JvmStatic
    @BindingAdapter("changeStatus")
    fun changeStatus(chip: Chip,status:Boolean){
        chip.isSelected = status
    }

}