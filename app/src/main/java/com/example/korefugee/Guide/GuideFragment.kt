package com.example.korefugee.Guide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.korefugee.R
import com.example.korefugee.databinding.FragmentGuideBinding

class GuideFragment : Fragment() {

    private lateinit var guidebinding : FragmentGuideBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        guidebinding = FragmentGuideBinding.inflate(inflater, container, false)




        return guidebinding.root

    }

}