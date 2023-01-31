package com.aplikasi.githubapiapps.model.response


import com.google.gson.annotations.SerializedName

data class ResponseRepoModels(
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("owner")
    val owner: ResponseUsersModel,
    @SerializedName("url")
    val url: String
)