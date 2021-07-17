package com.example.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.example.mysubscribers.data.db.entity.SubscriberEntity

interface SubscriberRepository {

    fun findSubscriber(id: Long): LiveData<SubscriberEntity>

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updtaetSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    fun getAllSubscribers(): LiveData<List<SubscriberEntity>>
}