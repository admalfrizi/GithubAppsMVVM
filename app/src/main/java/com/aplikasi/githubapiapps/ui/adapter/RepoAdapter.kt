package com.aplikasi.githubapiapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.RepoListViewBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoModels

class RepoAdapter(val context : Context, private val repoList: List<ResponseRepoModels>):
    RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

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
}