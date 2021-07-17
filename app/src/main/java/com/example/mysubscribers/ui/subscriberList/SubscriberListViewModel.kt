package com.example.mysubscribers.ui.subscriberList

import androidx.lifecycle.ViewModel
import com.example.mysubscribers.repository.SubscriberRepository

class SubscriberListViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    val allSubscribersEventData = repository.getAllSubscribers()

}