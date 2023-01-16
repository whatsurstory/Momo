package com.beva.momoapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.beva.momoapplication.R
import com.beva.momoapplication.ZooApplication
import com.beva.momoapplication.databinding.FragmentAnimalDetailBinding
import com.beva.momoapplication.model.ResultXXX
import com.beva.momoapplication.viewmodel.AnimalDetailViewModel

class AnimalDetailFragment : Fragment() {

    private lateinit var viewModel: AnimalDetailViewModel
    private lateinit var binding: FragmentAnimalDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimalDetailBinding.inflate(layoutInflater)
        arguments?.let {
            viewModel =
                AnimalDetailViewModel(AnimalDetailFragmentArgs.fromBundle(it).selectedProperty)
        }

        val imageAdapter = DetailImageAdapter()
        binding.animalDetailImageRecycler.adapter = imageAdapter
        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.animalDetailImageRecycler)
        viewModel.imageItem.observe(viewLifecycleOwner, Observer {
            imageAdapter.submitList(it)
        })

        viewModel.selectedProperty.observe(viewLifecycleOwner) { property ->
            property?.let {
                (activity as AppCompatActivity).supportActionBar?.title = property.a_name_ch
                updateUi(it)
            }
        }

        return binding.root
    }

    private fun updateUi(animalData: ResultXXX) {
        binding.animalDetailNameZh.text = animalData.a_name_ch
        binding.animalDetailNameEn.text = animalData.a_name_en
        binding.animalDetailAkaText.text =
            animalData.a_alsoknown.ifEmpty {
                ZooApplication.instance.resources.getString(
                    R.string.no_aka_information
                )
            }
        binding.animalDetailDistributionText.text = animalData.a_distribution
        binding.animalDetailBehaviorText.text = animalData.a_behavior
        binding.animalDetailFeatureText.text = animalData.a_feature
        binding.animalDetailUpdateText.text =
            if ((animalData.a_update == "########") || (animalData.a_update == "")) getString(R.string.no_update_information) else animalData.a_update
        binding.animalDetailDietText.text = animalData.a_diet
    }
}