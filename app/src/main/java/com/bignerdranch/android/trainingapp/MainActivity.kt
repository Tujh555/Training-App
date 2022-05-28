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
    private lateinit var service: ContactsService
    private var isServiceBound = false

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val permissionContactsReceived: Boolean
        get() = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

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
        setContentView(binding.root)

        binding.contactsList.layoutManager = LinearLayoutManager(this)

        binding.getContactsButton.setOnClickListener {
            if (isServiceBound && permissionContactsReceived) {
                updateUI()
            } else {
                requestContactsPermission()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        bindContactsService()
    }

    override fun onStop() {
        super.onStop()

        unbindService(connection)
        isServiceBound = false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_CONTACTS
            && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            bindContactsService()
        }
    }

    private fun bindContactsService() {
        Intent(this, ContactsService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    private fun getContacts(): List<String> {
        if (viewModel.contacts == null) {
            viewModel.contacts = service.loadContacts()
        }

        return viewModel.contacts ?: emptyList()
    }

    private fun updateUI() {
        val contacts = getContacts()

        binding.contactsList.adapter = ContactsAdapter(contacts)
        binding.hintTextView.isGone = contacts.isNotEmpty()
    }

    private fun requestContactsPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_CONTACTS),
            REQUEST_CODE_CONTACTS
        )
    }

    companion object {
        private const val REQUEST_CODE_CONTACTS = 1
    }
}