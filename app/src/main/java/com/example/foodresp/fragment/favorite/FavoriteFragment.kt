package com.example.foodresp.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.data.model.Result
import com.example.foodresp.databinding.FragmentFavoriteBinding
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
        return binding.root
    }

}