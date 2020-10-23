package com.fosusstart.task2.ui.main.contentProvider

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fosusstart.task2.R


class ContactsDataAdapter(private val contacts: List<ContactItem>):
    RecyclerView.Adapter<ContactsDataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactItem: ContactItem = contacts[position]
        holder.bind(contactItem)
    }

    override fun getItemCount(): Int = contacts.size

    class ViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.contacts_item, parent, false)) {
        private var nameView: TextView? = null
        private var phoneView: TextView? = null

        init {
            nameView = itemView.findViewById(R.id.contact_name_view)
            phoneView = itemView.findViewById(R.id.contact_phone_view)
        }

        fun bind(contact: ContactItem) {
            nameView?.text = contact.name
            phoneView?.text = contact.phone
        }
    }
}