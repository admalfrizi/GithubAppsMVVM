package com.aplikasi.githubapiapps.ui.tabfragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.FragmentRepoBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoModels
import com.aplikasi.githubapiapps.ui.adapter.RepoAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State


class RepoFragment : Fragment() {
    private var _binding : FragmentRepoBinding? = null
    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var repoAdapter: RepoAdapter

    private lateinit var repo: ArrayList<ResponseRepoModels>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepoBinding.inflate(inflater, container, false)

        getRepos()
        refreshButton()
        setupAdapter()

        return binding.root
    }

    private fun onRefresh() {
        repo.clear()
        getRepos()
    }

    private fun refreshButton() {
        binding.refresh.setOnRefreshListener(this::onRefresh)
    }

    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataRepo.layoutManager = layoutManager
        repo = ArrayList()
        repoAdapter = RepoAdapter(requireActivity(), repo)
        binding.dataRepo.adapter = repoAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getRepos() {
        viewModel.getRepos().observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.refresh.isRefreshing = false

                    val data = it.data

                    repo.addAll(data!!)

                    if(repoAdapter.itemCount == 0){
                        binding.emptyTv.visibility = View.VISIBLE
                        binding.refresh.isRefreshing = false
                    }

                    repoAdapter.notifyDataSetChanged()
                }
                State.ERROR -> {
                    binding.refresh.isRefreshing = false
                    binding.errorTv.visibility = View.VISIBLE
                    Log.d(ContentValues.TAG,"Error " + it.msg)
                }
                State.LOADING -> {
                    binding.refresh.isRefreshing = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Override
    override fun onStart() {
        super.onStart()
    }
}