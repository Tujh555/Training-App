package com.bignerdranch.android.trainingapp

import android.content.ContentResolver
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.recyclerview.ContactsAdapter
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private val contacts = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.get_contacts_button)

        val contactsList = findViewById<RecyclerView>(R.id.contacts_recycler_view).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val hint = findViewById<TextView>(R.id.hint_text_view)

        hint.isGone = contacts.size == 0

        button.setOnClickListener {
            val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null
            )

            cursor?.let {
                while (it.moveToNext()) {
                    val columnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

                    if (columnIndex >= 0) {
                        contacts.add(it.getString(columnIndex))
                    }
                }

                contactsList.adapter = ContactsAdapter(contacts)
                button.isGone = true

                it.close()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        if (ActivityCompat
                .checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_CONTACTS),
                REQUEST_CODE_CONTACTS
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    companion object {
        const val REQUEST_CODE_CONTACTS = 1
    }
}