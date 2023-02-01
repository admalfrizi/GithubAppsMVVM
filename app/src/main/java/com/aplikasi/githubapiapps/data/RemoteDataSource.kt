package com.aplikasi.githubapiapps.data

import com.aplikasi.githubapiapps.app.Routes
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val api : Routes) {

    suspend fun getUsers() = api.getUsers()

    suspend fun getRepos() = api.getRepos()

    suspend fun getUserDetails(name : String?) = api.getUserDetails(name)

    suspend fun getUserRepos(nameUser : String) = api.getUserRepo(nameUser)

    suspend fun getUserFollowers(nameUser: String) = api.getUserFollowers(nameUser)

    suspend fun getUserFollowings(nameUser: String) = api.getUserFollowings(nameUser)

    suspend fun getSearchUsers(nameQuery: String) = api.getSearchingUsers(nameQuery)

    suspend fun getSearchRepos(repoQuery: String) = api.getSearchingRepos(repoQuery)
}