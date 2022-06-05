package com.bignerdranch.android.trainingapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.trainingapp.databinding.FragmentGraphFirstBinding

class FirstGraphFragment : Fragment() {
    private var vb: FragmentGraphFirstBinding? = null

    private val navigateToSecondGraphFragmentListener = View.OnClickListener {
        val action = if (vb != null) {
            FirstGraphFragmentDirections
                .actionFirstGraphFragment2ToSecondGraphFragment2(
                    vb!!.sentDataEditTextFragment.text.toString()
                )
        } else {
            FirstGraphFragmentDirections.actionFirstGraphFragment2ToSecondGraphFragment2()
        }


        this.findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentGraphFirstBinding.inflate(inflater, container, false)

        vb?.run {
            navigateToSecondGraphFragment.setOnClickListener(navigateToSecondGraphFragmentListener)
        }

        return vb?.root
    }
}