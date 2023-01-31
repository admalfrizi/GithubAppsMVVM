package com.aplikasi.githubapiapps.ui.userDetailsFragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.FragmentFollowerBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.adapter.FollowersAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State

@RequiresApi(Build.VERSION_CODES.M)
class FollowerFragment(private val nameUser: String) : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var followerAdapter: FollowersAdapter
    private lateinit var follower: ArrayList<ResponseUsersModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater, container,false)

        setupFollowerData()
        refreshButton()
        setupAdapter()
        return binding.root
    }

    private fun onRefresh() {
        follower.clear()
        setupFollowerData()
    }

    private fun refreshButton() {
        binding.refresh.setOnRefreshListener(this::onRefresh)
    }

    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataUserFollower.layoutManager = layoutManager
        follower = ArrayList()
        followerAdapter = FollowersAdapter(requireContext(), follower)
        binding.dataUserFollower.adapter = followerAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupFollowerData() {
        viewModel.getUserFollowers(nameUser).observe(viewLifecycleOwner){
            when(it.state){
                State.SUCCESS -> {
                    val data = it.data

                    follower.addAll(data!!)

                    if(followerAdapter.itemCount == 0){
                        binding.emptyTv.visibility = View.VISIBLE
                        binding.refresh.isRefreshing = false
                    }

                    followerAdapter.notifyDataSetChanged()
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

}