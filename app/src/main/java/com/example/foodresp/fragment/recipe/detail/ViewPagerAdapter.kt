package com.example.foodresp.fragment.recipe.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *@Description
 *@Author PC
 *@QQ 1578684787
 */
class ViewPagerAdapter(
    private val fragments:List<Fragment>,
    fm:FragmentManager,
    lifecycle:Lifecycle
    ):FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}