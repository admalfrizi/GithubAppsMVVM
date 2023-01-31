package com.aplikasi.githubapiapps.ui.adapter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aplikasi.githubapiapps.ui.userDetailsFragment.FollowerFragment
import com.aplikasi.githubapiapps.ui.userDetailsFragment.FollowingFragment
import com.aplikasi.githubapiapps.ui.userDetailsFragment.RepoListFragment

@RequiresApi(Build.VERSION_CODES.M)
class UserDetailsAdapter(private val nameUser : String, fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle) {


    override fun getItemCount(): Int {
        return 3
    }


    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                RepoListFragment(nameUser)
            }
            1 -> {
                FollowerFragment(nameUser)
            }
            2 -> {
                FollowingFragment(nameUser)
            }
            else -> {
                Fragment()
            }
        }
    }

}