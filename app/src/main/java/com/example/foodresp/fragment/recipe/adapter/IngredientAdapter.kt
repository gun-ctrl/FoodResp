package com.example.foodresp.fragment.recipe.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.data.model.ExtendedIngredient
import com.example.foodresp.databinding.FragmentIngredientBinding
import com.example.foodresp.databinding.IngredientItemBinding

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class IngredientAdapter:RecyclerView.Adapter<IngredientAdapter.MyViewHolder>() {
    private var ingredientList:List<ExtendedIngredient> = emptyList()
    class MyViewHolder(val binding: IngredientItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = IngredientItemBinding.inflate(inflater)
                return MyViewHolder(binding)
            }
        }
        fun bind(ingredient:ExtendedIngredient){
            binding.ingredient = ingredient
            //开始渲染
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(ingredientList[position])
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }
    //这里不用DiffUtil 因为这里的数据是不会变的（没有增、删、改、查等操作）
    fun setData(newData:List<ExtendedIngredient>){
        ingredientList = newData
        notifyDataSetChanged()
    }

}