package com.fernanortega.mymoneycount.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.fernanortega.mymoneycount.data.database.entities.RecentSearchQueryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentSearchQueryDao {
    @Query("SELECT * FROM recent_search_query_table ORDER BY queried_date DESC LIMIT :limit")
    fun getRecentSearchQueries(limit: Int): Flow<List<RecentSearchQueryEntity>>

    @Upsert
    suspend fun insertOrReplaceRecentSearchQuery(recentSearchQuery: RecentSearchQueryEntity)

    @Query(value = "DELETE FROM recent_search_query_table")
    suspend fun clearRecentSearchQueries()
}