package com.example.foodresp.fragment.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.utils.NetworkResult
import com.example.foodresp.databinding.FragmentRecipeBinding
import com.example.foodresp.viewModel.MainViewModel


class RecipeFragment : Fragment() {
    private lateinit var binding: FragmentRecipeBinding
    private val typeAdapter = TypeAdapter()
    private val foodAdapter = FoodAdapter()
    private val mainViewModel:MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecipeBinding.inflate(inflater)
        initRecyclerView()
        initFoodRecyclerView()
        mainViewModel.recipes.observe(viewLifecycleOwner, Observer {
            when(it){
                is NetworkResult.Success ->{
                    binding.foodRecyclerView.hideShimmer()
                    foodAdapter.setData(it.data!!.results)
                }
                is NetworkResult.Loading ->{
                    binding.foodRecyclerView.showShimmer()
                }
                is NetworkResult.Error ->{
                    binding.foodRecyclerView.hideShimmer()
                    Toast.makeText(requireContext(),"Get Recipes Failed:${it.message}",Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })


        return binding.root
    }


    private fun initRecyclerView(){
        //配置类型选择的RecyclerView
        binding.typeRecyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.HORIZONTAL,false)
        binding.typeRecyclerView.adapter = typeAdapter
        //处理回调事件
        typeAdapter.callBack= {current, last ->
            val currentHolder = binding.typeRecyclerView.findViewHolderForAdapterPosition(current) as TypeAdapter.MyViewHolder
            val lastHolder = binding.typeRecyclerView.findViewHolderForAdapterPosition(last)
            //选中当前
            currentHolder.changeSelectedStatus(true)
            //取消之前选中的
            if (lastHolder != null){
                val lastTypeHolder = lastHolder as TypeAdapter.MyViewHolder
                lastTypeHolder.changeSelectedStatus(false)
            }else{
                //重新把上一次选中的item刷新
                typeAdapter.notifyItemChanged(last)
            }
            //获取数据
            fetchData(typeAdapter.typeList[current])
        }

    }
    private fun initFoodRecyclerView(){
        binding.foodRecyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.foodRecyclerView.adapter = foodAdapter

    }

    private fun fetchData(type:String){
        mainViewModel.fetchFoodRecipes(type)
    }
}