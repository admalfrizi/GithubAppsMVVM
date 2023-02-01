package com.aplikasi.githubapiapps.ui.searchFragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.FragmentUsersSearchBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.adapter.UserSearchAdapter
import com.aplikasi.githubapiapps.ui.navscreen.search.SearchViewModel
import com.aplikasi.githubapiapps.util.State
import java.util.*

@RequiresApi(Build.VERSION_CODES.M)
class UsersSearchFragment : Fragment() {

    private var _binding : FragmentUsersSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var user : ArrayList<ResponseUsersModel>
    private lateinit var userAdapter: UserSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUsersSearchBinding.inflate(layoutInflater, container, false)

        setupEditText()
        setupAdapter()
        return binding.root
    }


    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataUsersSearch.layoutManager = layoutManager
        user = ArrayList()
        userAdapter = UserSearchAdapter(requireActivity(), user)
        binding.dataUsersSearch.adapter = userAdapter
    }

    private fun setupEditText() {
        binding.usersQuery.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                getUsersData(s.toString())
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getUsersData(text: String) {

        viewModel.getSearchingUsers(text).observe(viewLifecycleOwner){
            when (it.state) {
                State.SUCCESS -> {

                    val dataSource = it.data

                    userAdapter.setUserListItems(dataSource!!.items as MutableList<ResponseUsersModel>)
                    userAdapter.filter.filter(text)

                    if(userAdapter.itemCount == 0){
                        binding.emptyTv.visibility = View.VISIBLE
                    }

                }
                State.LOADING -> {
                }
                else -> {}
            }
        }
    }

}