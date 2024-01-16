package com.fernanortega.mymoneycount.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fernanortega.mymoneycount.domain.model.RecentSearchQuery
import kotlinx.datetime.Instant

@Entity(
    tableName = "recent_search_query_table"
)
data class RecentSearchQueryEntity(
    @PrimaryKey val query: String,
    @ColumnInfo("queried_date") val queriedDate: Instant
) {
    fun toModel(): RecentSearchQuery = RecentSearchQuery(
        query = query, queriedDate = queriedDate
    )
}
