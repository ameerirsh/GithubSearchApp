package com.ameer.githubsearch.data

data class Repository(
    val id: Long,
    val name: String,
    val full_name: String,
    val description: String?,
    val owner: Owner,
    val stargazers_count: Int
)
