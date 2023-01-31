package com.aplikasi.githubapiapps.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.RepoUserListViewBinding
import com.aplikasi.githubapiapps.model.response.ResponseRepoUserModel

class RepoListAdapter(val context : Context, private val repoUserList: List<ResponseRepoUserModel>):
    RecyclerView.Adapter<RepoListAdapter.RepoUserViewHolder>() {

    inner class RepoUserViewHolder(val binding: RepoUserListViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoUserViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = RepoUserListViewBinding.inflate(view, parent, false)

        return RepoUserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return repoUserList.size
    }

    override fun onBindViewHolder(holder: RepoUserViewHolder, position: Int) {
        val item = repoUserList[position]
        holder.binding.name.text = item.name
        holder.binding.ownerName.text = item.owner.login
        holder.binding.fullName.text = item.fullName
    }
}