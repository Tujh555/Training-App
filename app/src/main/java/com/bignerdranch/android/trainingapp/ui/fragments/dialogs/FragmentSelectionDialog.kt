package com.bignerdranch.android.trainingapp.ui.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.databinding.FragmentDialogBinding

class FragmentSelectionDialog : Fragment(R.layout.fragment_dialog) {
    private var binding: FragmentDialogBinding? = null

    private val chooseGetDataFragmentListener = View.OnClickListener {
        FragmentSelectionDialogDirections
            .actionFragmentSelectionDialogToGetDataFragment()
            .also { navDirections ->
                findNavController().navigate(navDirections)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.chooseGetDataFragmentButton?.setOnClickListener(chooseGetDataFragmentListener)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        fun newInstance(args: Bundle? = null) = FragmentSelectionDialog().apply {
            arguments = args
        }
    }
}