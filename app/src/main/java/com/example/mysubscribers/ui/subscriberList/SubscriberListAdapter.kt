package com.example.mysubscribers.ui.subscriberList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysubscribers.R
import com.example.mysubscribers.data.db.entity.SubscriberEntity
import kotlinx.android.synthetic.main.subscriber_item.view.*

class SubscriberListAdapter(
    val subscribes: List<SubscriberEntity>,
    private val onItemClickListener: ((subscriber: SubscriberEntity) -> Unit)
) :
    RecyclerView.Adapter<SubscriberListAdapter.SubscriberListAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubscriberListAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.subscriber_item, parent, false)
        return SubscriberListAdapterViewHolder(view, onItemClickListener)

    }

    override fun onBindViewHolder(holder: SubscriberListAdapterViewHolder, position: Int) {
        holder.bindView(subscribes[position])
    }

    override fun getItemCount(): Int {
        return subscribes.size
    }

    class SubscriberListAdapterViewHolder(
        itemView: View,
        private val onItemClickListener: ((subscriber: SubscriberEntity) -> Unit)
    ) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.text_name
        val email = itemView.text_email

        fun bindView(subscriberEntity: SubscriberEntity) {

            name.text = subscriberEntity.name
            email.text = subscriberEntity.email

            itemView.setOnClickListener {
                onItemClickListener(subscriberEntity)
            }
        }

    }
}