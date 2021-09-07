package com.example.foodresp.fragment.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodresp.R
import com.example.foodresp.databinding.FragmentSummerBinding
import org.jsoup.Jsoup


class SummerFragment(private val summary:String) : Fragment() {
    private lateinit var binding:FragmentSummerBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSummerBinding.inflate(inflater)
        binding.summaryTextView.text = Jsoup.parse(summary).text()
        return binding.root
    }


}