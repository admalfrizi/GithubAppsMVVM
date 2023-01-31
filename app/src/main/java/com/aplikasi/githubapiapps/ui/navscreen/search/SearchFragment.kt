package com.aplikasi.githubapiapps.ui.navscreen.search

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.aplikasi.githubapiapps.databinding.FragmentSearchBinding
import com.aplikasi.githubapiapps.ui.adapter.SearchingAdapter
import com.google.android.material.tabs.TabLayoutMediator

@RequiresApi(Build.VERSION_CODES.M)
class SearchFragment : Fragment() {

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        setupSearchUI()
        return binding.root

    }

    private fun setupSearchUI() {
        val searchTabAdapter = SearchingAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = searchTabAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            when(position){
                0 -> {
                    tab.text = "User"
                }
                1 -> {
                    tab.text = "Repos"
                }
            }
        }.attach()
    }


}