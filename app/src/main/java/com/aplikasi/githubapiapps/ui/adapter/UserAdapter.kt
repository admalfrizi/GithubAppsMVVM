package com.aplikasi.githubapiapps.ui.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.UserListViewBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.details.DetailsUser
import com.bumptech.glide.Glide

@RequiresApi(Build.VERSION_CODES.M)
class UserAdapter(private val context : Context, private var userList: List<ResponseUsersModel>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: UserListViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = UserListViewBinding.inflate(view, parent, false)

        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]

        holder.binding.username.text = item.login
        Glide.with(context).load(item.avatarUrl).into(holder.binding.imgProfile)
        holder.binding.userCard.setOnClickListener {
            val intent = Intent(context, DetailsUser::class.java)

            intent.putExtra("username", item.login)
            intent.putExtra("img", item.avatarUrl)

            context.startActivity(intent)
        }
    }
}