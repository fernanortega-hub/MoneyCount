package com.fernanortega.mymoneycount.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernanortega.mymoneycount.data.database.dao.AccountDao
import com.fernanortega.mymoneycount.data.database.dao.RegisterDao
import com.fernanortega.mymoneycount.data.database.entities.AccountEntity
import com.fernanortega.mymoneycount.data.database.entities.RegisterEntity

@Database(
    entities = [
        AccountEntity::class,
        RegisterEntity::class
    ],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
abstract class MyMoneyCountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun registerDao(): RegisterDao
}