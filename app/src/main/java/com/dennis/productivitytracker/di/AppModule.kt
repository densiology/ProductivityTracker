package com.dennis.productivitytracker.di

import android.app.Application
import androidx.room.Room
import com.dennis.productivitytracker.data.ProdTrackerDB
import com.dennis.productivitytracker.data.ProdTrackerRepo
import com.dennis.productivitytracker.data.ProdTrackerRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProdTrackerDB(app: Application): ProdTrackerDB {
        return Room.databaseBuilder(
            app,
            ProdTrackerDB::class.java,
            "prod_tracker_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideProdTrackerRepo(db: ProdTrackerDB): ProdTrackerRepo {
        return ProdTrackerRepoImpl(db.dao)
    }
}