package com.example.foodresp.fragment.recipe.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
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
        binding.backBtn.setOnClickListener{
            requireActivity().onBackPressed()
        }
        binding.detailBtn.setOnClickListener {
            if (!binding.detailBtn.isSelected){
                binding.detailBtn.isSelected = true
                binding.ingredientBtn.isSelected = false
                indicatorAnim(0f)
            }
        }
        binding.ingredientBtn.setOnClickListener {
            if (!binding.ingredientBtn.isSelected){
                binding.ingredientBtn.isSelected = true
                binding.detailBtn.isSelected = false
                val space = binding.detailBtn.x - binding.ingredientBtn.x
                indicatorAnim(space)
            }
        }
    }

    private fun indicatorAnim(value:Float){
        binding.indicatorView.animate()
                .translationX(value)
                .setDuration(300)
                .start()
    }
}