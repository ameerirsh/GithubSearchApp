package com.ameer.githubsearchapp

import com.ameer.githubsearch.data.GithubApiService
import com.ameer.githubsearch.data.Repository

class GithubRepository(
    private val apiService: GithubApiService
) {
    suspend fun searchRepositories(query: String): List<Repository> {
        return apiService.searchRepositories(query).items
    }
}

