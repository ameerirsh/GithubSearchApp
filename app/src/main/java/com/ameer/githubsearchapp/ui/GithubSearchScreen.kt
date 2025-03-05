package com.ameer.githubsearch

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ameer.githubsearch.data.Repository

@Composable
fun GithubSearchScreen(viewModel: GithubViewModel = viewModel()) {
    var query by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Enter Github repo") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.searchRepositories(query) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SEARCH")
        }
        Spacer(modifier = Modifier.height(16.dp))

        val repoList by viewModel.repoList.collectAsState()

        LazyColumn {
            items(repoList) { repo ->
                RepoItem(repo)
            }
        }
    }
}

@Composable
fun RepoItem(repo: Repository) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Use CardDefaults
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(repo.owner.avatar_url),
                contentDescription = "Avatar",
                modifier = Modifier.size(50.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = repo.full_name, style = MaterialTheme.typography.bodySmall)
                Text(text = repo.description ?: "No description available")
                Text(text = "⭐ ${repo.stargazers_count}")
            }
        }
    }
}
