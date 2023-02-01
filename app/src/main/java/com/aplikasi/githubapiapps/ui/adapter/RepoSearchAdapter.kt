package com.aplikasi.githubapiapps.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.RepoListViewBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoModels

class RepoSearchAdapter(val context : Context, private var repoList: List<ResponseRepoModels> = listOf()):
    RecyclerView.Adapter<RepoSearchAdapter.RepoViewHolder>(), Filterable {

    var repoData : MutableList<ResponseRepoModels> = mutableListOf()

    inner class RepoViewHolder(val binding: RepoListViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RepoListViewBinding.inflate(view, parent, false)

        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = repoList[position]
        holder.binding.name.text = item.name
        holder.binding.ownerName.text = item.owner.login
        holder.binding.nodeId.text = item.owner.nodeId
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setReposListItems(repo: MutableList<ResponseRepoModels>){
        repoData = repo.toMutableList()
        repoList = repo
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun getFilter() : Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val repoQuery = constraint.toString()
                val searchResults = FilterResults()

                searchResults.values = if(repoQuery.isEmpty()){
                    repoData
                } else {
                    repoData.filter {
                        it.name.contains(repoQuery, ignoreCase = true) || it.name.contains(constraint!!)
                    }
                }

                return searchResults
            }
            
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                repoList = results?.values as List<ResponseRepoModels>
                notifyDataSetChanged()
            }

        }
    }
}