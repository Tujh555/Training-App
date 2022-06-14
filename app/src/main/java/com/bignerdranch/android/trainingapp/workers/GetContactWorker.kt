package com.bignerdranch.android.trainingapp.workers

import android.content.Context
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.bignerdranch.android.trainingapp.Constants
import kotlin.random.Random
import com.bignerdranch.android.trainingapp.R
import kotlinx.coroutines.delay

class GetContactWorker(
    private val context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {
    private val contacts by lazy { loadContacts() }
    private val randomContact: String
        get() = contacts.random()

    init {
        Log.d(Constants.TAG, "Worker was initialized")
    }

    override suspend fun doWork(): Result {
        return try {
            val contact = randomContact
            val workData = workDataOf(Constants.CONTACT_NAME_KEY to contact)
            Log.d(Constants.TAG, "doWork is running")
            delay(1000L)
            Log.d(Constants.TAG, "doWork ends")
            Result.success(workData)
        } catch (e: Exception) {
            Log.d(Constants.TAG, "doWork failed")
            Result.failure()
        }
    }

    private fun loadContacts(): List<String> {
        val cursor = context.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null
        )
        val contacts = mutableListOf<String>()

        return cursor?.let {
            while (it.moveToNext()) {
                val columnIndex = it.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

                if (columnIndex >= 0) {
                    contacts.add(it.getString(columnIndex))
                }
            }

            it.close()
            Log.d(Constants.TAG, "contacts was uploaded")
            contacts
        } ?: listOf("Loading was failed")
    }

    private suspend fun showNotification() {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(context, Constants.WORKER_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_book_24)
                    .setContentText("Worker work")
                    .setContentTitle("Get contact worker")
                    .build()
            )
        )
    }
}