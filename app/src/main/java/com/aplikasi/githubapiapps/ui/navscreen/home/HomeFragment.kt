package com.aplikasi.githubapiapps.ui.navscreen.home

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.aplikasi.githubapiapps.databinding.FragmentHomeBinding
import com.aplikasi.githubapiapps.ui.adapter.TabAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.M)
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUI()

        return root
    }

    private fun setupUI() {
        val adapter = TabAdapter(childFragmentManager,lifecycle)
        binding.viewPagerw.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPagerw){ tab, position ->
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