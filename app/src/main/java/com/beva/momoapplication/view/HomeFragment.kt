package com.beva.momoapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.beva.momoapplication.databinding.FragmentHomeBinding
import com.beva.momoapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        val viewModel = HomeViewModel()
        val adapter = HomeAdapter()
        binding.listRecycler.adapter = adapter
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
        return binding.root
    }

}