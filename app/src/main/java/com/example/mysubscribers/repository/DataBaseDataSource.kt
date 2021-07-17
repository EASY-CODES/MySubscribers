package com.example.mysubscribers.repository

import androidx.lifecycle.LiveData
import com.example.mysubscribers.data.db.dao.SubscriberDAO
import com.example.mysubscribers.data.db.entity.SubscriberEntity

class DataBaseDataSource(private val subscriberDAO: SubscriberDAO) : SubscriberRepository {

    override fun findSubscriber(id: Long): LiveData<SubscriberEntity> {
        return subscriberDAO.find(id)
    }

    override suspend fun insertSubscriber(name: String, email: String): Long {

        val subscriber = SubscriberEntity(
            name = name,
            email = email
        )
        return subscriberDAO.insert(subscriber)
    }

    override suspend fun updtaetSubscriber(id: Long, name: String, email: String) {
        val subscriber = SubscriberEntity(
            id = id,
            name = name,
            email = email
        )
        return subscriberDAO.update(subscriber)
    }

    override suspend fun deleteSubscriber(id: Long) {
        subscriberDAO.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        subscriberDAO.deleteAll()
    }

    override fun getAllSubscribers(): LiveData<List<SubscriberEntity>> {
        return subscriberDAO.getAll()
    }
}