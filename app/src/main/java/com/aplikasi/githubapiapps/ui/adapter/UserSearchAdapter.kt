package com.aplikasi.githubapiapps.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.aplikasi.githubapiapps.databinding.UserListViewBinding
import com.aplikasi.githubapiapps.model.response.ResponseUsersModel
import com.aplikasi.githubapiapps.ui.details.DetailsUser
import com.bumptech.glide.Glide

@RequiresApi(Build.VERSION_CODES.M)
class UserSearchAdapter(private val context : Context, private var userList: List<ResponseUsersModel> = listOf()) :
    RecyclerView.Adapter<UserSearchAdapter.UserViewHolder>(), Filterable {

    var userData : MutableList<ResponseUsersModel> = mutableListOf()

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


    @SuppressLint("NotifyDataSetChanged")
    fun setUserListItems(user: MutableList<ResponseUsersModel>){
        userData = user.toMutableList()
        userList = user
        notifyDataSetChanged()
    }


    override fun getFilter() : android.widget.Filter  {
        return object : android.widget.Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint.toString()
                val filterResults = FilterResults()

                filterResults.values = if(queryString.isEmpty()){
                    userData
                } else {
                    userData.filter {
                        it.login.contains(queryString, ignoreCase = true) || it.login.contains(constraint!!)
                    }
                }

                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                userList = results?.values as List<ResponseUsersModel>
                notifyDataSetChanged()

            }

        }
    }


}