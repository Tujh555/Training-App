package com.bignerdranch.android.trainingapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bignerdranch.android.trainingapp.Callback
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.databinding.FragmentFirstBinding

class SecondFragment : Fragment() {
    private var vb: FragmentFirstBinding? = null

    private val navigateToSecondFragmentListener = View.OnClickListener {
        val message = vb?.sentDataEditTextFragment?.text?.toString() ?: ""

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SecondFragment.newInstance(message))
            .addToBackStack(null)
            .commit()
    }

    private val navigateToFirstActivityButtonListener = View.OnClickListener {
        val activity = requireActivity()

        try {
            val callback = activity as Callback

            callback.setConstraintLayoutVisibility(View.VISIBLE)
            activity.supportFragmentManager.popBackStack()
        } catch (e: ClassCastException) {
            Log.e("TAG", "Activity wasn't MainActivity")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentFirstBinding.inflate(inflater, container, false)

        vb?.sentDataEditTextFragment?.setText(
            arguments?.getString(FIRST_ACTIVITY_MESSAGE) ?: "Ничего не добавлено"
        )

        vb?.run {
            navigateToSecondFragment.setOnClickListener(navigateToSecondFragmentListener)

            navigateToFirstActivityButton.setOnClickListener(navigateToFirstActivityButtonListener)
        }

        return vb?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        vb = null
    }

    companion object {
        private const val FIRST_ACTIVITY_MESSAGE = "firstActivityMessage"

        fun newInstance(message: String): FirstFragment {
            val args = Bundle().apply { putString(FIRST_ACTIVITY_MESSAGE, message) }
            return FirstFragment().apply { arguments = args }
        }
    }
}