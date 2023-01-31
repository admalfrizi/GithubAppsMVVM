package com.aplikasi.githubapiapps.ui.userDetailsFragment

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
import com.aplikasi.githubapiapps.databinding.FragmentRepoListBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoUserModel
import com.aplikasi.githubapiapps.ui.adapter.RepoListAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State

class RepoListFragment(private val nameUser: String) : Fragment() {

    private lateinit var binding: FragmentRepoListBinding

    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var repoAdapter: RepoListAdapter
    private lateinit var repo: ArrayList<ResponseRepoUserModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepoListBinding.inflate(layoutInflater)

        setupData()
        refreshButton()
        setupAdapter()
        return binding.root
    }

    private fun onRefresh() {
        repo.clear()
        setupData()
    }

    private fun refreshButton() {
        binding.refresh.setOnRefreshListener(this::onRefresh)
    }

    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataRepoUser.layoutManager = layoutManager
        repo = ArrayList()
        repoAdapter = RepoListAdapter(requireContext(), repo)
        binding.dataRepoUser.adapter = repoAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupData() {
        viewModel.getUserRepos(nameUser).observe(viewLifecycleOwner){
            when(it.state){
                State.SUCCESS -> {
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

    @Override
    override fun onStart() {
        super.onStart()
    }

}