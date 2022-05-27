package com.bignerdranch.android.trainingapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.trainingapp.databinding.ActivityMainBinding
import com.bignerdranch.android.trainingapp.recyclerview.ContactsAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var service: ContactsService
    private var isServiceBound = false

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            p1?.let {
                val binder = it as ContactsService.ContactsBinder
                service = binder.getService()
                isServiceBound = true
            }
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isServiceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.contactsList.layoutManager = LinearLayoutManager(this)

        binding.getContactsButton.setOnClickListener {
            Log.d("MainActivity", checkPermission().toString())
            if (isServiceBound && checkPermission()) {
                updateUI()
            } else {
                Log.d("MainActivity", "Request")
                requestPermission()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, ContactsService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
        isServiceBound = false
    }

    private fun getContacts(): List<String> {
        if (viewModel.contacts == null) {
            viewModel.contacts = service.loadContacts()
        }

        return viewModel.contacts!!
    }

    private fun updateUI() {
        val contacts = getContacts()
        binding.contactsList.adapter = ContactsAdapter(contacts)
        binding.hintTextView.isGone = contacts.isNotEmpty()
    }

    private fun checkPermission() = ActivityCompat.checkSelfPermission(
        this,
        android.Manifest.permission.READ_CONTACTS
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermission() = ActivityCompat.requestPermissions(
        this,
        arrayOf(android.Manifest.permission.READ_CONTACTS),
        REQUEST_CODE_CONTACTS
    )

    companion object {
        private const val REQUEST_CODE_CONTACTS = 1
    }
}