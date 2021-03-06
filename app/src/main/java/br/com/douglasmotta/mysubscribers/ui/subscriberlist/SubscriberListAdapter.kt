package br.com.douglasmotta.mysubscribers.ui.subscriberlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.douglasmotta.mysubscribers.R
import br.com.douglasmotta.mysubscribers.data.db.entity.SubscriberEntity
import kotlinx.android.synthetic.main.subscriber_item.view.*

class SubscriberListAdapter(
    private val subscribers: List<SubscriberEntity>
) : RecyclerView.Adapter<SubscriberListAdapter.SubscriberListViewHolder>() {

    var onItemClick: ((entity: SubscriberEntity) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriberListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.subscriber_item, parent, false)
        return SubscriberListViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubscriberListViewHolder, position: Int) {
        holder.bindView(subscribers[position])
    }

    override fun getItemCount() = subscribers.size


    inner class SubscriberListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textViewSubscriberName: TextView = itemView.text_subscreiber_name
        private val textViewSubscriberEmail: TextView = itemView.text_subscreiber_email

        fun bindView(subscriber: SubscriberEntity) {
            textViewSubscriberName.text = subscriber.name
            textViewSubscriberEmail.text = subscriber.email

            itemView.setOnClickListener{
                onItemClick?.invoke(subscriber)
            }
        }
    }
}