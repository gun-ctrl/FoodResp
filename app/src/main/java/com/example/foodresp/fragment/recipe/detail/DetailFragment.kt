package com.example.foodresp.fragment.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.foodresp.databinding.FragmentDetailBinding

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class DetailFragment:Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private val recipeArgs:DetailFragmentArgs by navArgs()
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initEvent()
        initViewPager()
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