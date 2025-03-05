package com.ameer.githubsearch


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameer.githubsearch.data.Repository
import com.ameer.githubsearchapp.data.repository.GithubRepository
import com.ameer.githubsearchapp.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GithubViewModel: ViewModel() {
    private val repository = GithubRepository(RetrofitClient.apiService)
    private val _repoList = MutableStateFlow<List<Repository>>(emptyList())
    val repoList: StateFlow<List<Repository>> = _repoList

    fun searchRepositories(query: String) {
        viewModelScope.launch {
            _repoList.value = repository.searchRepositories(query)
        }
    }
}