package com.example.mysubscribers.ui.subscriberList

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.mysubscribers.MainActivity.Companion.navigateWithAnimations
import com.example.mysubscribers.R
import com.example.mysubscribers.data.db.AppDatabase
import com.example.mysubscribers.data.db.entity.SubscriberEntity
import com.example.mysubscribers.repository.DataBaseDataSource
import com.example.mysubscribers.ui.subscriber.SubscriberFragment
import com.example.mysubscribers.ui.subscriber.SubscriberViewModel
import kotlinx.android.synthetic.main.subscriber_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubscriberListFragment : Fragment(R.layout.subscriber_list_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireView().hideKeyboard()

        val viewModel: SubscriberListViewModel by viewModel()

        viewModel.allSubscribersEventData.observe(viewLifecycleOwner) { res ->

            val subscriberListAdapter = SubscriberListAdapter(res) { subscriber ->
                val directions =
                    SubscriberListFragmentDirections.actionSubscriberListFragmentToSubscriberFragment(
                        subscriber
                    )
                findNavController().navigateWithAnimations(
                    directions.actionId,
                    directions.arguments
                )

            }

            rv_subscribers.run {
                setHasFixedSize(true)
                adapter = subscriberListAdapter
            }

        }


        add_subscriber.setOnClickListener {
            val directions =
                SubscriberListFragmentDirections.actionSubscriberListFragmentToSubscriberFragment(
                    null
                )
            findNavController().navigateWithAnimations(
                directions.actionId,
                directions.arguments
            )
        }


    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }


}