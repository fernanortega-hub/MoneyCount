package com.fernanortega.mymoneycount.domain.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class RecentSearchQuery(
    val query: String,
    val queriedDate: Instant = Clock.System.now()
)
