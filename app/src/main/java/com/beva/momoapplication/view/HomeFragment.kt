package com.beva.momoapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beva.momoapplication.LoadApiStatus
import com.beva.momoapplication.R
import com.beva.momoapplication.databinding.FragmentHomeBinding
import com.beva.momoapplication.getVmFactory
import com.beva.momoapplication.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by viewModels<HomeViewModel> { getVmFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.zoo)

        val adapter = HomeAdapter(HomeAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.listRecycler.adapter = adapter
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.status.observe(viewLifecycleOwner, Observer {
            bindApiStatus(binding.progressStatus, it)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer{
            bindApiErrorMessage(binding.errorMessageText, it)
        })

        binding.layoutSwipeRefreshHome.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.refreshStatus.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    binding.layoutSwipeRefreshHome.isRefreshing = it
                }
            }
        )

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToHouseDetailFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }


    private fun bindApiStatus(view: ProgressBar, status: LoadApiStatus?) {
        when (status) {
            LoadApiStatus.LOADING -> view.visibility = View.VISIBLE
            LoadApiStatus.DONE, LoadApiStatus.ERROR -> view.visibility = View.GONE
            else -> {}
        }
    }

    private fun bindApiErrorMessage(view: TextView, message: String?) {
        when (message) {
            null, "" -> {
                view.visibility = View.GONE
            }
            else -> {
                view.text = message.toString()
                view.visibility = View.VISIBLE
            }
        }
    }

}