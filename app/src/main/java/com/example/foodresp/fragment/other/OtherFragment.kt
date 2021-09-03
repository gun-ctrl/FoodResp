package com.example.foodresp.fragment.other

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodresp.R
import com.example.foodresp.databinding.FragmentOtherBinding


class OtherFragment : Fragment() {
    private lateinit var binding: FragmentOtherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }


}