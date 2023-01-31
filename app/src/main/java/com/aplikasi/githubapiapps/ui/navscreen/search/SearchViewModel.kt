package com.aplikasi.githubapiapps.ui.navscreen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aplikasi.githubapiapps.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repo : AppRepository
) : ViewModel() {

    fun getSearchingUsers(nameQuery: String) = repo.getSearchingUsers(nameQuery).asLiveData()
}