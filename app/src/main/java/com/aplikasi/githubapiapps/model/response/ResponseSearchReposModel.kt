package com.aplikasi.githubapiapps.model.response


import com.google.gson.annotations.SerializedName

data class ResponseSearchReposModel(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<ResponseRepoModels>,
    @SerializedName("total_count")
    val totalCount: Int
)