package com.example.mysubscribers.ui.subscriber

import android.util.Log
import androidx.lifecycle.*
import com.example.mysubscribers.R
import com.example.mysubscribers.repository.DataBaseDataSource
import com.example.mysubscribers.repository.SubscriberState
import com.example.mysubscribers.repository.SubscriberRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class SubscriberViewModel(
    private val repository: SubscriberRepository
) : ViewModel() {

    private val _subscriberState: MutableLiveData<SubscriberState> = MutableLiveData()
    val subscriberState: LiveData<SubscriberState> get() = _subscriberState

    fun addOrSubscriber(id: Long, name: String, email: String) {

        if (id > 0) {
            updSubscriber(id, name, email)
        } else {
            addSubscriber(name, email)
        }
    }


    fun delSubscriber(id: Long) = viewModelScope.launch {

        try {

            repository.deleteSubscriber(id)

            _subscriberState.value = SubscriberState.SucessDelete(
                R.string.subscriber_deleted_sucessfully
            )

        } catch (ex: Exception) {
            _subscriberState.value = SubscriberState.Error(R.string.subscriber_error_to_delete)
            Log.e(TAG, ex.toString())
        }

    }


    private fun updSubscriber(id: Long, name: String, email: String) = viewModelScope.launch {
        try {

            repository.updtaetSubscriber(id, name, email)

            _subscriberState.value = SubscriberState.SucessUpdate(
                R.string.subscriber_updated_sucessfully
            )

        } catch (ex: Exception) {
            _subscriberState.value = SubscriberState.Error(R.string.subscriber_error_to_update)
            Log.e(TAG, ex.toString())
        }
    }


    private fun addSubscriber(name: String, email: String) = viewModelScope.launch {
        try {

            val id = repository.insertSubscriber(name, email)
            if (id > 0) {
                _subscriberState.value = SubscriberState.SucessInsert(
                    R.string.subscriber_inserted_sucessfully,
                    id
                )
            }

        } catch (ex: Exception) {
            _subscriberState.value = SubscriberState.Error(R.string.subscriber_error_to_insert)
            Log.e(TAG, ex.toString())
        }
    }


    companion object {
        val TAG = "SUBSCRIBTAGDEBUG"
    }


}