package com.aplikasi.githubapiapps.ui.details

import android.content.ContentValues
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.aplikasi.githubapiapps.databinding.ActivityDetailsUserBinding
import com.aplikasi.githubapiapps.ui.adapter.UserDetailsAdapter
import com.aplikasi.githubapiapps.ui.navscreen.home.HomeViewModel
import com.aplikasi.githubapiapps.util.State
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.M)
class DetailsUser : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsUserBinding

    private val viewModel : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("username")
        val img = intent.getStringExtra("img")

        setupData(name!!, img!!)
        setupViewPager(name)

    }

    private fun setupViewPager(name: String) {
        val tabAdapter = UserDetailsAdapter(name,supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, position ->
            when(position){
                0 -> {
                    tab.text = "Repositories"
                }
                1-> {
                    tab.text = "Followers"
                }
                2->{
                    tab.text = "Following"
                }
            }
        }.attach()
    }

    private fun setupData(name: String, img: String) {
        binding.apply {
            Glide.with(this@DetailsUser).load(img).into(binding.imgProfile)
            username.text = name
            binding.btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }

        detailsData(name)
    }

    private fun detailsData(name: String) {
        viewModel.getUserDetails(name).observe(this){
            when(it.state){
                State.SUCCESS -> {
                    val data = it.data

                    binding.apply {
                        nameUser.text = data?.name
                        followersTv.text = data?.followers.toString()
                        followingTv.text = data?.following.toString()
                        reposTv.text = data?.publicRepos.toString()
                    }
                }
                State.ERROR -> {
                    Log.d(ContentValues.TAG,"Error " + it.msg)
                }
                State.LOADING -> {

                }
            }
        }
    }
}