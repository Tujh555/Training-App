package com.bignerdranch.android.trainingapp.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.R

class ContactHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val contactTextView = itemView.findViewById<TextView>(R.id.contact_text_view)

    fun bind(contact: String) {
        contactTextView.text = contact
    }
}