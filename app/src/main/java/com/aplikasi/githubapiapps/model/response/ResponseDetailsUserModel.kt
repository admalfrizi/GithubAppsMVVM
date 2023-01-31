package com.aplikasi.githubapiapps.model.response


import com.google.gson.annotations.SerializedName

data class ResponseDetailsUserModel(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("bio")
    val bio: Any?,
    @SerializedName("blog")
    val blog: String,
    @SerializedName("company")
    val company: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email: Any?,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: String,
    @SerializedName("login")
    val login: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("public_repos")
    val publicRepos: Int,
    @SerializedName("site_admin")
    val siteAdmin: Boolean,
    @SerializedName("twitter_username")
    val twitterUsername: Any?,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_at")
    val updatedAt: String,
)