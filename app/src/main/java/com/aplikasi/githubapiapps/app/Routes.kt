package com.aplikasi.githubapiapps.app

import com.aplikasi.githubapiapps.model.response.*
import com.aplikasi.githubapiapps.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Routes {

    @GET(Constants.USERS_URL)
    suspend fun getUsers(): Response<List<ResponseUsersModel>>

    @GET(Constants.REPOS_URL)
    suspend fun getRepos(): Response<List<ResponseRepoModels>>

    @GET(Constants.USERS_DETAIL_URL + "{user}")
    suspend fun getUserDetails(
        @Path("user") nameUser : String? = null
    ) : Response<ResponseDetailsUserModel>

    @GET(Constants.BASE_URL + "users/{nameUser}/repos")
    suspend fun getUserRepo(
        @Path("nameUser") nameUser: String?
    ) : Response<List<ResponseRepoUserModel>>

    @GET(Constants.BASE_URL + "users/{nameUser}/followers")
    suspend fun getUserFollowers(
        @Path("nameUser") nameUser: String?
    ) : Response<List<ResponseUsersModel>>

    @GET(Constants.BASE_URL + "users/{nameUser}/following")
    suspend fun getUserFollowings(
        @Path("nameUser") nameUser: String?
    ) : Response<List<ResponseUsersModel>>

    @GET(Constants.SEARCH_USERS_URL)
    suspend fun getSearchingUsers(
        @Query("q") q: String?
    ): Response<ResponseSearchUsersModel>
}