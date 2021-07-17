package com.example.mysubscribers.repository

import com.example.mysubscribers.data.db.entity.SubscriberEntity

sealed class SubscriberState {


    class SucessFind(val message: Int, val subscriberEntity: SubscriberEntity) :
        SubscriberState()

    class SucessInsert(val message: Int, val id: Long) : SubscriberState()
    class SucessUpdate(val message: Int) : SubscriberState()
    class SucessDelete(val message: Int) : SubscriberState()
    class SucessDeleteAll(val message: Int) : SubscriberState()
    class SucessGetAll(val message: String, val subscribers: List<SubscriberEntity>) :
        SubscriberState()

    class Error(val message: Int) : SubscriberState()

}