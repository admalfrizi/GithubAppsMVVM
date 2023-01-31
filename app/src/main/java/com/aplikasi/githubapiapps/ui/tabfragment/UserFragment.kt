package com.aplikasi.githubapiapps.ui.tabfragment

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
import com.aplikasi.githubapiapps.databinding.FragmentUserBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.adapter.UserAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State

@RequiresApi(Build.VERSION_CODES.M)
class UserFragment : Fragment() {
    private var _binding : FragmentUserBinding? = null
    private val viewModel : HomeViewModel by activityViewModels()
    private lateinit var userAdapter: UserAdapter

    private lateinit var user : ArrayList<ResponseUsersModel>
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getData()
        refreshButton()
        setupAdapter()
        return root
    }

    private fun onRefresh() {
        user.clear()
        getData()
    }

    private fun refreshButton() {
        binding.refresh.setOnRefreshListener(this::onRefresh)
    }


    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataUser.layoutManager = layoutManager
        user = ArrayList()
        userAdapter = UserAdapter(requireActivity(), user)
        binding.dataUser.adapter = userAdapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getData() {
        viewModel.getUsers().observe(viewLifecycleOwner) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.refresh.isRefreshing = false

                    val data = it.data

                    user.addAll(data!!)

                    if(userAdapter.itemCount == 0){
                        binding.emptyTv.visibility = View.VISIBLE
                        binding.refresh.isRefreshing = false
                    }

                    userAdapter.notifyDataSetChanged()

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