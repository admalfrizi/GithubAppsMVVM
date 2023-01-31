package com.aplikasi.githubapiapps.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRepoUserModel (
    @SerializedName("id")
    val id: Long,

    @SerializedName("node_id")
    val nodeid: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("full_name")
    val fullName: String,

    @SerializedName("owner")
    val owner: ResponseUsersModel,

    @SerializedName("html_url")
    val htmlurl: String,

    @SerializedName("descriptio")
    val description: String?,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("pushed_at")
    val pushedAt: String,
)

