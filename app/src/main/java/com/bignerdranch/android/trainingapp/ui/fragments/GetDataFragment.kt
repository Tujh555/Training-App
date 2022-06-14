package com.bignerdranch.android.trainingapp.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.bignerdranch.android.trainingapp.Constants
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.databinding.FragmentGetDataBinding
import com.bignerdranch.android.trainingapp.workers.GetContactWorker

class GetDataFragment : Fragment(R.layout.fragment_get_data) {
    private var binding: FragmentGetDataBinding? = null

    private val isPermissionGranted: Boolean
        get() = ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED

    private val liveDataObserver = Observer<WorkInfo> { workInfo ->
        binding?.run {
            receivedData.text = when (workInfo.state) {
                WorkInfo.State.SUCCEEDED -> getString(
                    R.string.received_data_text,
                    workInfo.outputData.getString(Constants.CONTACT_NAME_KEY)
                )

                WorkInfo.State.RUNNING -> "Ожидание ответа от workManager"

                WorkInfo.State.FAILED -> "Не удалось получить контакт((("

                WorkInfo.State.CANCELLED -> "Операция отменена"

                else -> "Ожидание"
            }
        }
    }

    private val getDataListener = View.OnClickListener {
        Log.d(Constants.TAG, "Button was clicked")
        if (isPermissionGranted) {
            Log.d(Constants.TAG, "PermissionGranted")
            getContact()
        } else {
            Log.d(Constants.TAG, "Request permission")
            requestReadContactsPermission()
        }
    }

    private val sendDataListener = View.OnClickListener {
        val action = GetDataFragmentDirections
            .actionGetDataFragmentToLastFragment(
                binding?.receivedData?.text?.toString() ?: ""
            )

        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetDataBinding.inflate(layoutInflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            getDataButton.setOnClickListener(getDataListener)
            sentDataToLastFragment.setOnClickListener(sendDataListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun requestReadContactsPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.READ_CONTACTS),
            1
        )
    }

    private fun getContact() {
        Log.d(Constants.TAG, "RequestBuilder was called")
        val request = OneTimeWorkRequestBuilder<GetContactWorker>()
            .build()

        Log.d(Constants.TAG, "Work manager received")
        val workManager = WorkManager
            .getInstance(requireContext())

        Log.d(Constants.TAG, "Request was enqueued")
        workManager.enqueue(request)

        Log.d(Constants.TAG, "workInfo got observe")
        workManager.getWorkInfoByIdLiveData(request.id).observe(viewLifecycleOwner, liveDataObserver)
    }
}