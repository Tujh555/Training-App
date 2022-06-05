package com.bignerdranch.android.trainingapp.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.models.Contact

class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val contactTextView = itemView.findViewById<TextView>(R.id.contact_text_view)
    private val phoneNumberTextView = itemView.findViewById<TextView>(R.id.phone_number_text_view)

    fun bind(contact: Contact) {
        contactTextView.text = contact.name
        phoneNumberTextView.text = contact.phoneNumber
    }
}