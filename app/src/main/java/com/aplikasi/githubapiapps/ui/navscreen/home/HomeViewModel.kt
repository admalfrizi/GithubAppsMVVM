package com.aplikasi.githubapiapps.ui.navscreen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.githubapiapps.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo : AppRepository
) : ViewModel() {

    fun getUsers() = repo.getUsers().asLiveData()

    fun getRepos() = repo.getRepos().asLiveData()

    fun getUserDetails(name : String) = repo.getUserDetails(name).asLiveData()

    fun getUserRepos(nameUser : String) = repo.getUserRepos(nameUser).asLiveData()

    fun getUserFollowers(nameUser: String) = repo.getUserFollowers(nameUser).asLiveData()

    fun getUserFollowings(nameUser: String) = repo.getUserFollowings(nameUser).asLiveData()


}