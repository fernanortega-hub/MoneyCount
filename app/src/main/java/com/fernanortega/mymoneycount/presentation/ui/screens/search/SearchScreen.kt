package com.fernanortega.mymoneycount.presentation.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernanortega.mymoneycount.R
import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import com.fernanortega.mymoneycount.domain.model.UserSearchResult
import com.fernanortega.mymoneycount.presentation.ui.components.AccountItem
import com.fernanortega.mymoneycount.presentation.ui.components.RegisterItem
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    uiState: SearchState,
    onQueryChange: (String) -> Unit,
    onSearchExplicitly: (String) -> Unit,
    onClickQuery: (String) -> Unit,
    onBack: () -> Unit,
    onNavigateToAccount: (accountId: Int) -> Unit,
    onActiveChange: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back_label)
                )
            }
            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
            val maxHeight = remember(screenHeight) {
                screenHeight * (2f / 3f)
            }
            val minHeight = remember(maxHeight) {
                240.dp.coerceAtMost(maxHeight)
            }
            DockedSearchBar(
                query = uiState.query,
                onQueryChange = onQueryChange,
                onSearch = onSearchExplicitly,
                active = uiState.active,
                onActiveChange = onActiveChange,
                leadingIcon = {
                    Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
                },
                trailingIcon = if (uiState.query.isBlank()) null else {
                    {
                        IconButton(onClick = { onQueryChange("") }) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = stringResource(
                                    R.string.clear_query_label,
                                    uiState.query
                                )
                            )
                        }
                    }
                }
            ) {
                if (uiState.recentSearches.isNotEmpty()) {
                    uiState.recentSearches.forEach { recentSearch ->
                        ListItem(
                            modifier = Modifier
                                .clickable {
                                    onClickQuery(recentSearch.query)
                                },
                            headlineContent = {
                                Text(text = recentSearch.query)
                            }
                        )
                    }
                } else {
                    ListItem(
                        modifier = Modifier
                            .heightIn(minHeight, maxHeight)
                            .align(Alignment.CenterHorizontally),
                        headlineContent = {
                            Text(
                                text = stringResource(id = R.string.not_recent_searches),
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            }
        }
        if (uiState.isEmpty() && uiState.query.isNotBlank()) {
            NoResultsForQuery(
                modifier = Modifier
                    .weight(1f),
                query = uiState.query
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (uiState.results.registers.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = stringResource(id = R.string.register_label),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    items(
                        items = uiState.results.registers,
                        key = { "register_${it.id}" }
                    ) { register ->
                        RegisterItem(register = register)
                        if(uiState.results.registers.size > 1) {
                            HorizontalDivider()
                        }
                    }
                }

                if (uiState.results.accounts.isNotEmpty()) {
                    stickyHeader {
                        Text(
                            text = stringResource(id = R.string.accounts_label),
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.background)
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }

                    items(
                        items = uiState.results.accounts,
                        key = { "account_${it.id}" }
                    ) { account ->
                        AccountItem(
                            account = account,
                            onClick = {
                                onNavigateToAccount(account.id)
                            }
                        )
                        if(uiState.results.accounts.size > 1) {
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NoResultsForQuery(
    modifier: Modifier = Modifier,
    query: String
) {
    Column(
        modifier = modifier
            .padding(horizontal = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val noResults = stringResource(R.string.query_not_found_results_label, query)
        val start = noResults.indexOf(query)
        Text(
            text = AnnotatedString(
                text = noResults,
                spanStyles = listOf(
                    AnnotatedString.Range(
                        item = SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = start + query.length
                    )
                )
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 24.dp)
        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    MaterialTheme {
        Surface {
            SearchScreen(
                uiState = SearchState(
                    query = "dfdafda",
                    active = false,
                    recentSearches = persistentListOf(
                        RecentSearchQuery(
                            "ewqewwq"
                        )
                    ),
                    results = UserSearchResult(
                        accounts = listOf(),
                        registers = listOf()
                    )

                ),
                onQueryChange = {},
                onSearchExplicitly = {},
                onBack = { },
                onNavigateToAccount = {},
                onActiveChange = {},
                onClickQuery = {}
            )
        }
    }
}