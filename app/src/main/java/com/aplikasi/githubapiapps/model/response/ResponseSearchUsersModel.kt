package com.aplikasi.githubapiapps.model.response


import com.google.gson.annotations.SerializedName

data class ResponseSearchUsersModel(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<ResponseUsersModel>,
    @SerializedName("total_count")
    val totalCount: Int
)