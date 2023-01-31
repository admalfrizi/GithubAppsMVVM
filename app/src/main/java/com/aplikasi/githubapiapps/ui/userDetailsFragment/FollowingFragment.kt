package com.aplikasi.githubapiapps.ui.userDetailsFragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.FragmentFollowingBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.adapter.FollowingAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State

@RequiresApi(Build.VERSION_CODES.M)
class FollowingFragment(private val nameUser: String) : Fragment() {

    private lateinit var binding: FragmentFollowingBinding

    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var followingAdapter: FollowingAdapter
    private lateinit var following: ArrayList<ResponseUsersModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowingBinding.inflate(inflater, container,false)

        setupFollowingData()
        refreshButton()
        setupAdapter()
        return binding.root
    }

    private fun onRefresh() {
        following.clear()
        setupFollowingData()
    }

    private fun refreshButton() {
        binding.refresh.setOnRefreshListener(this::onRefresh)
    }


    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataUserFollowing.layoutManager = layoutManager
        following = ArrayList()
        followingAdapter = FollowingAdapter(requireContext(), following)
        binding.dataUserFollowing.adapter = followingAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupFollowingData() {
        viewModel.getUserFollowings(nameUser).observe(viewLifecycleOwner){
            when(it.state){
                State.SUCCESS -> {
                    val data = it.data

                    following.addAll(data!!)

                    if(followingAdapter.itemCount == 0){
                        binding.emptyTv.visibility = View.VISIBLE
                        binding.refresh.isRefreshing = false
                    }

                    followingAdapter.notifyDataSetChanged()
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