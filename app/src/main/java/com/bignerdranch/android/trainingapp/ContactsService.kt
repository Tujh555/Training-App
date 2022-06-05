package com.bignerdranch.android.trainingapp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.provider.ContactsContract
import android.util.Log
import com.bignerdranch.android.trainingapp.models.Contact

class ContactsService : Service() {
    private val binder = ContactsBinder()
    private val contacts = mutableListOf<Contact>()

    override fun onBind(p0: Intent?): IBinder = binder

    fun loadContacts(): List<Contact> {
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null
        )

        return cursor?.let {
            while (it.moveToNext()) {

                val columnIndexName = it.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
                )
                val columnIndexPhoneNumber = it.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.NUMBER
                )

                val newContact = Contact().apply {
                    name = if (columnIndexName > 0) {
                        it.getString(columnIndexName)
                    } else {
                        name
                    }
                    phoneNumber = if (columnIndexPhoneNumber > 0) {
                        it.getString(columnIndexPhoneNumber)
                    } else {
                        phoneNumber
                    }
                }

                contacts.add(newContact)
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