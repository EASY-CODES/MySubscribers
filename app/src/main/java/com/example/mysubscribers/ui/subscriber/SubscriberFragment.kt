package com.example.mysubscribers.ui.subscriber


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mysubscribers.MainActivity.Companion.navigateWithAnimations
import com.example.mysubscribers.R
import com.example.mysubscribers.repository.SubscriberState
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.subscriber_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SubscriberFragment : Fragment(R.layout.subscriber_fragment) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: SubscriberFragmentArgs by navArgs()
        val viewModel: SubscriberViewModel by viewModel()

        var id: Long = 0

        args.subscriber?.let { subscriber ->
            id = subscriber.id
            input_name.setText(subscriber.name)
            input_email.setText(subscriber.email)
            button_subiscriber.text = resources.getString(R.string.subscriber_button_update)
            button_subiscriber_delete.visibility = Button.VISIBLE
        }

        addObserversViweModel(viewModel)

        button_subiscriber.setOnClickListener {
            viewModel.addOrSubscriber(
                id,
                input_name.text.toString(),
                input_email.text.toString()
            )
        }

        button_subiscriber_delete.setOnClickListener {
            viewModel.delSubscriber(id)
        }


    }

    fun addObserversViweModel(viewModel: SubscriberViewModel) {
        viewModel.subscriberState.observe(viewLifecycleOwner) { result ->
            when (result) {

                is SubscriberState.SucessInsert -> {
                    Log.d(
                        SubscriberViewModel.TAG,
                        resources.getString(result.message) + "  ID: " + result.id
                    )

                    Snackbar.make(
                        requireView(),
                        resources.getString(result.message),
                        Snackbar.LENGTH_LONG
                    ).show()

                    clearFields()
                    requireView().hideKeyboard()

                    findNavController().navigateWithAnimations(
                        R.id.action_subscriberFragment_to_subscriberListFragment,
                        null
                    )

                }

                is SubscriberState.SucessUpdate -> {
                    Log.d(
                        SubscriberViewModel.TAG,
                        resources.getString(result.message)
                    )

                    Snackbar.make(
                        requireView(),
                        resources.getString(result.message),
                        Snackbar.LENGTH_LONG
                    ).show()

                    clearFields()
                    requireView().hideKeyboard()

                    findNavController().navigateWithAnimations(
                        R.id.action_subscriberFragment_to_subscriberListFragment,
                        null
                    )

                }

                is SubscriberState.SucessDelete -> {
                    Log.d(
                        SubscriberViewModel.TAG,
                        resources.getString(result.message)
                    )

                    Snackbar.make(
                        requireView(),
                        resources.getString(result.message),
                        Snackbar.LENGTH_LONG
                    ).show()

                    clearFields()
                    requireView().hideKeyboard()

                    findNavController().navigateWithAnimations(
                        R.id.action_subscriberFragment_to_subscriberListFragment,
                        null
                    )

                }


                is SubscriberState.Error -> {
                    Log.d(SubscriberViewModel.TAG, resources.getString(result.message))

                    Snackbar.make(
                        requireView(),
                        resources.getString(result.message),
                        Snackbar.LENGTH_LONG
                    ).show()

                    requireView().hideKeyboard()
                }

            }

        }

    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun clearFields() {
        input_email.text?.clear()
        input_name.text?.clear()
    }


}