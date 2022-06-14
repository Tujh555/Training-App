package com.bignerdranch.android.trainingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.databinding.FragmentLastBinding

class LastFragment : Fragment(R.layout.fragment_last) {
    private var binding: FragmentLastBinding? = null
    private val lastFragmentArgs by navArgs<LastFragmentArgs>()

    private val navigateToMainFragmentListener = View.OnClickListener {
        val action = LastFragmentDirections.actionLastFragmentToMainFragment()

        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLastBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            receivedDataFromFragment.text = lastFragmentArgs.contactData
            navigateToMainFragment.setOnClickListener(navigateToMainFragmentListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }
}