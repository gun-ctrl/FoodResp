package com.example.foodresp.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.data.model.Result
import com.example.foodresp.databinding.FragmentFavoriteBinding
import com.example.foodresp.utils.showToast
import com.example.foodresp.viewModel.FavoriteViewModel


class FavoriteFragment : Fragment() {
    private lateinit var binding:FragmentFavoriteBinding
    private val favoriteAdapter = FavoriteAdapter()
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.recyclerView.adapter = favoriteAdapter
        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner){
            val resultList = mutableListOf<Result>()
            it.forEach {entity->
                resultList.add(entity.recipe)
            }
            favoriteAdapter.setData(resultList)
        }
        swipeToDelete()
        return binding.root
    }

    /**
     * 滑动删除
     */
    private fun swipeToDelete(){
        val itemTouchHelper = ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                //获取滑动的位置
                val index = viewHolder.adapterPosition
                //将数据从数据源中删除
                val data = favoriteViewModel.favoriteRecipes.value!![index]
                favoriteViewModel.deleteFavorite(data)
                //弹出提示信息
               Toast.makeText(requireContext(),"Delete Finished!",Toast.LENGTH_LONG).show()
            }

        })
        //将滑动事件绑定到recyclerView上
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }
}