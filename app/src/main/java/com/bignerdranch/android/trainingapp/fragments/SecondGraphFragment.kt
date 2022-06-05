package com.bignerdranch.android.trainingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.trainingapp.databinding.FragmentGraphSecondBinding

class SecondGraphFragment : Fragment() {
    private var vb: FragmentGraphSecondBinding? = null

    private val args: SecondGraphFragmentArgs by navArgs()
    private val navigateToFirstFragmentListener = View.OnClickListener {
        val action = SecondGraphFragmentDirections
            .actionSecondGraphFragment2ToFirstGraphFragment22()

        this.findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentGraphSecondBinding.inflate(inflater, container, false)

        vb?.run {
            receivedDataTextViewFragment.text = args.firstFragmentMessage

            navigateToFirstGraphFragmentButtonFragment
                .setOnClickListener(navigateToFirstFragmentListener)
        }

        return vb?.root
    }
}