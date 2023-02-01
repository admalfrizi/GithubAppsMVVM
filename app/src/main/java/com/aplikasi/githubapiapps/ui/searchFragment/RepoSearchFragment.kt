package com.aplikasi.githubapiapps.ui.searchFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.FragmentRepoSearchBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoModels
import com.aplikasi.githubapiapps.ui.adapter.RepoSearchAdapter
import com.aplikasi.githubapiapps.ui.navscreen.search.SearchViewModel
import com.aplikasi.githubapiapps.util.State


class RepoSearchFragment : Fragment() {

    private var _binding : FragmentRepoSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var repos : ArrayList<ResponseRepoModels>
    private lateinit var repoSearchAdapter: RepoSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRepoSearchBinding.inflate(layoutInflater, container, false)

        setupEdText()
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.dataReposSearch.layoutManager = layoutManager
        repos = ArrayList()
        repoSearchAdapter = RepoSearchAdapter(requireActivity(), repos)
        binding.dataReposSearch.adapter = repoSearchAdapter
    }

    private fun setupEdText() {
        binding.repoQuery.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                getRepoDataList(s.toString())
            }

        })
    }
    @SuppressLint("NotifyDataSetChanged")
    private fun getRepoDataList(text: String) {
        viewModel.getSearchingRepos(text).observe(viewLifecycleOwner){
            when(it.state){
                State.SUCCESS -> {

                    val dataSource = it.data

                    repoSearchAdapter.setReposListItems(dataSource!!.items as MutableList<ResponseRepoModels>)
                    repoSearchAdapter.filter.filter(text)

                    if(repoSearchAdapter.itemCount == 0){
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