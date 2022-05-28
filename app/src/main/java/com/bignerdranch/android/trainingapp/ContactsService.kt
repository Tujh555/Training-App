package com.bignerdranch.android.trainingapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.provider.ContactsContract
import android.util.Log

class ContactsService : Service() {
    private val binder = ContactsBinder()
    private val contacts = mutableListOf<String>()

    override fun onBind(p0: Intent?): IBinder = binder

    fun loadContacts(): List<String> {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null
        )

        return cursor?.let {
            while (it.moveToNext()) {
                val columnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

                if (columnIndex >= 0) {
                    contacts.add(it.getString(columnIndex))
                }
            }

            it.close()
            Log.d("MainActivity", "Contacts was uploaded")
            contacts
        } ?: emptyList()
    }

    inner class ContactsBinder : Binder() {
        fun getService(): ContactsService = this@ContactsService
    }
}