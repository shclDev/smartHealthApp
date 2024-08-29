package com.shcl.smarthealth.di

import android.app.Application
import androidx.room.Room
import com.shcl.smarthealth.data.db.LocalDB
import com.shcl.smarthealth.data.db.LocalDBDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideDatabase(app : Application) : LocalDB =
        Room.databaseBuilder(app , LocalDB::class.java ,"shcl_db").fallbackToDestructiveMigration().build()

    @Provides
    fun provideLocalDBDao(localDB: LocalDB) : LocalDBDao = localDB.localDBDao()

}