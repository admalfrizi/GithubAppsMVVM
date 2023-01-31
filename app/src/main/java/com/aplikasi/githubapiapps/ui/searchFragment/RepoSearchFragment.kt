package com.aplikasi.githubapiapps.ui.searchFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aplikasi.githubapiapps.databinding.FragmentRepoSearchBinding
import com.aplikasi.githubapiapps.ui.navscreen.search.SearchViewModel


class RepoSearchFragment() : Fragment() {

    private var _binding : FragmentRepoSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRepoSearchBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}