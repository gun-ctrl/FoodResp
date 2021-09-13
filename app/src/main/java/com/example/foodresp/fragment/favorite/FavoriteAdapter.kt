package com.example.foodresp.fragment.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.data.model.Result
import com.example.foodresp.databinding.FavoriteItemBinding

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.MyViewHolder>() {
    private var recipeList:List<Result> = emptyList()
    class MyViewHolder(val binding:FavoriteItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):MyViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemBinding.inflate(inflater,parent,false)
                return MyViewHolder(binding)
            }
        }
        fun bind(result: Result){
            binding.recipe = result
            binding.executePendingBindings()
            binding.favoritecontainer.setOnClickListener {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(result)
                binding.favoritecontainer.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }
    fun setData(newData:List<Result>){
        recipeList = newData
        notifyDataSetChanged()
    }
}