package com.beva.momoapplication.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.beva.momoapplication.ZooApplication
import com.beva.momoapplication.getOrAwaitValue
import com.beva.momoapplication.source.ZooRepository
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Before
    fun setUpViewModel () {
        val zooRepository = ZooApplication.instance.zooRepository
        homeViewModel = HomeViewModel(zooRepository)
    }
    @Test
    fun getZooProperties_network_success(){

        homeViewModel.getZooProperties()

        assertThat(homeViewModel.properties.getOrAwaitValue(), `is`(true))

    }

}