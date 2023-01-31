package com.aplikasi.githubapiapps.ui.adapter

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aplikasi.githubapiapps.ui.searchFragment.RepoSearchFragment
import com.aplikasi.githubapiapps.ui.searchFragment.UsersSearchFragment

@RequiresApi(Build.VERSION_CODES.M)
class SearchingAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm,lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> {
                UsersSearchFragment()
            }
            1 -> {
                RepoSearchFragment()
            }
            else -> {
                Fragment()
            }
        }
    }

}