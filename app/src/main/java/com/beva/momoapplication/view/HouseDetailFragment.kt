package com.beva.momoapplication.view

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.beva.momoapplication.CurrentFragmentType
import com.beva.momoapplication.R
import com.beva.momoapplication.ZooApplication
import com.beva.momoapplication.databinding.FragmentHouseDetailBinding
import com.beva.momoapplication.getVmFactory

import com.beva.momoapplication.loadImage
import com.beva.momoapplication.model.ResultX
import com.beva.momoapplication.viewmodel.HomeViewModel
import com.beva.momoapplication.viewmodel.HouseDetailVieModel

class HouseDetailFragment : Fragment() {

    private lateinit var binding: FragmentHouseDetailBinding

    private val viewModel by viewModels<HouseDetailVieModel> { getVmFactory(HouseDetailFragmentArgs.fromBundle(requireArguments()).selectedProperty) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHouseDetailBinding.inflate(inflater)
//        arguments?.let {
//            viewModel =
//                HouseDetailVieModel(HouseDetailFragmentArgs.fromBundle(it).selectedProperty)
//        }

        val adapter = AnimalsAdapter(AnimalsAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })
        binding.houseDetailRecycler.adapter = adapter
        viewModel.properties.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.selectedProperty.observe(viewLifecycleOwner) { property ->
            property?.let {
                (activity as AppCompatActivity).supportActionBar?.title = it.name
                updateUi(it)
            }
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            it?.let {
                findNavController().navigate(
                    HouseDetailFragmentDirections.actionHouseDetailFragmentToAnimalDetailFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }

    private fun updateUi(property: ResultX) {
        binding.houseDetailImage.loadImage(property.picUrl)
        binding.houseDetailContent.text = property.info
        binding.houseDetailMemo.text =
            if (property.memo?.isNotEmpty() == true) property.memo else ZooApplication.instance.resources.getString(
                R.string.no_operation_information
            )

        // set up spanned string with url
        val spannableString =
            SpannableString(getString(R.string.web_open))

        spannableString
            .setSpan(
                URLSpan(property.url),
                0,
                binding.houseDetailWeb.text.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

        binding.houseDetailWeb.text = spannableString
        // enable clicking on url span
        binding.houseDetailWeb.movementMethod = LinkMovementMethod.getInstance()

        binding.houseDetailCategory.text = property.category
    }

}