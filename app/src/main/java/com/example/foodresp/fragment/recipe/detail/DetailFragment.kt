package com.example.foodresp.fragment.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.foodresp.databinding.FragmentDetailBinding
import com.example.foodresp.viewModel.FavoriteViewModel

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class DetailFragment:Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private val recipeArgs:DetailFragmentArgs by navArgs()
    private val favoriteViewModel:FavoriteViewModel by viewModels()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailBtn.isSelected = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initEvent()
        initViewPager()
        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner) {
            it.forEach {entity->
               if (entity.recipe == recipeArgs.recipe){
                   binding.collectBtn.isSelected = true
                   return@forEach
               }
            }
        }
    }

    private fun indicatorAnim(value:Float){
        binding.indicatorView.animate()
                .translationX(value)
                .setDuration(300)
                .start()
    }

    private fun initViewPager(){
        val fragments = listOf(SummerFragment(recipeArgs.recipe.summary),IngredientFragment(recipeArgs.recipe.extendedIngredients))
        binding.viewPager.adapter = ViewPagerAdapter(fragments,requireActivity().supportFragmentManager,lifecycle)
    }
    private fun initEvent(){
        binding.backBtn.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.detailBtn.setOnClickListener {
            selectDetail()
            binding.viewPager.currentItem = 0
        }
        binding.ingredientBtn.setOnClickListener {
            selectIngredient()
            binding.viewPager.currentItem = 1
        }
        binding.collectBtn.setOnClickListener {
            if (binding.collectBtn.isSelected){
                //从数据库收藏表中删除这个食谱
                favoriteViewModel.favoriteRecipes.value?.forEach{entity->
                    if (entity.recipe == recipeArgs.recipe){
                        favoriteViewModel.deleteFavorite(entity)
                        binding.collectBtn.isSelected = false
                    }
                }

            }else{
                //插入数据表中
                favoriteViewModel.insertFavorite(recipeArgs.recipe)
                binding.collectBtn.isSelected = true
            }
        }
        binding.viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if (position==0){
                    selectDetail()
                }else{
                    selectIngredient()
                }
            }
        })
    }
    private fun selectDetail(){
        if (!binding.detailBtn.isSelected){
            binding.detailBtn.isSelected = true
            binding.ingredientBtn.isSelected = false
            indicatorAnim(0f)
        }
    }
    private fun selectIngredient(){
        if (!binding.ingredientBtn.isSelected){
            binding.ingredientBtn.isSelected = true
            binding.detailBtn.isSelected = false
            val space = binding.ingredientBtn.x - binding.detailBtn.x
            indicatorAnim(space)
        }
    }
}