package com.bignerdranch.android.trainingapp.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.models.Contact

class ContactsAdapter(private val contacts: List<Contact>) : RecyclerView.Adapter<ContactHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_contact_item, parent, false)

        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) = holder.bind(contacts[position])

    override fun getItemCount() = contacts.size
}