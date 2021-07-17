package com.example.mysubscribers.di

import android.app.Application
import androidx.room.Room
import com.example.mysubscribers.data.db.AppDatabase
import com.example.mysubscribers.data.db.dao.SubscriberDAO
import com.example.mysubscribers.repository.DataBaseDataSource
import com.example.mysubscribers.repository.SubscriberRepository
import com.example.mysubscribers.ui.subscriber.SubscriberViewModel
import com.example.mysubscribers.ui.subscriberList.SubscriberListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


val databaseModule = module {

    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "app_database")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideSubscriberDAO(database: AppDatabase): SubscriberDAO {
        return database.subscriberDAO
    }

    fun provideDataBaseDataSource(subscriberDAO: SubscriberDAO): SubscriberRepository {
        return DataBaseDataSource(subscriberDAO)
    }


    single { provideAppDatabase(androidApplication()) }
    single { provideSubscriberDAO(get()) }
    single { provideDataBaseDataSource(get()) }
}


val subscribeModule = module {

    viewModel {
        SubscriberViewModel(get())
    }
    viewModel {
        SubscriberListViewModel(get())
    }
}