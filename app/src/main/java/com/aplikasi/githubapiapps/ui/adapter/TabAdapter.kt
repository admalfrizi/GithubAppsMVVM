package com.aplikasi.githubapiapps.ui.adapter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aplikasi.githubapiapps.ui.tabfragment.RepoFragment
import com.aplikasi.githubapiapps.ui.tabfragment.UserFragment

class TabAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                UserFragment()
            }
            1 -> {
                RepoFragment()
            }
            else -> {
                Fragment()
            }
        }
    }

}