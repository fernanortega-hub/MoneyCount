package com.fernanortega.mymoneycount.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fernanortega.mymoneycount.data.database.dao.AccountDao
import com.fernanortega.mymoneycount.data.database.dao.RecentSearchQueryDao
import com.fernanortega.mymoneycount.data.database.dao.RegisterDao
import com.fernanortega.mymoneycount.data.database.entities.AccountEntity
import com.fernanortega.mymoneycount.data.database.entities.RecentSearchQueryEntity
import com.fernanortega.mymoneycount.data.database.entities.RegisterEntity
import com.fernanortega.mymoneycount.data.database.util.InstantConverter

@Database(
    entities = [
        AccountEntity::class,
        RegisterEntity::class,
        RecentSearchQueryEntity::class
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(
    InstantConverter::class
)
abstract class MyMoneyCountDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun registerDao(): RegisterDao
    abstract fun recentSearchQueryDao(): RecentSearchQueryDao
}