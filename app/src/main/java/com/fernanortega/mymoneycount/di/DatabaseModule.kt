package com.fernanortega.mymoneycount.di

import android.content.Context
import androidx.room.Room
import com.fernanortega.mymoneycount.data.database.MyMoneyCountDatabase
import com.fernanortega.mymoneycount.data.database.dao.AccountDao
import com.fernanortega.mymoneycount.data.database.dao.RegisterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesMyMoneyCountDatabase(
        @ApplicationContext context: Context
    ): MyMoneyCountDatabase = Room.databaseBuilder(
        context = context,
        klass = MyMoneyCountDatabase::class.java,
        name = "my_money_count_database"
    ).build()

    @Provides
    @Singleton
    fun providesAccountDao(
        database: MyMoneyCountDatabase
    ): AccountDao = database.accountDao()

    @Provides
    @Singleton
    fun providesRegisterDao(
        database: MyMoneyCountDatabase
    ): RegisterDao = database.registerDao()
}