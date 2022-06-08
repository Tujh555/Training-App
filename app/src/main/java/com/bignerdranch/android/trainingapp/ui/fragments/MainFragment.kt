package com.bignerdranch.android.trainingapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.trainingapp.R
import com.bignerdranch.android.trainingapp.data.Icon
import com.bignerdranch.android.trainingapp.data.Memory
import com.bignerdranch.android.trainingapp.databinding.FragmentMainBinding
import com.bignerdranch.android.trainingapp.ui.adapters.CardAdapter
import com.bignerdranch.android.trainingapp.ui.adapters.IconAdapter
import com.bignerdranch.android.trainingapp.ui.adapters.decoration.IconDecoration

class MainFragment : Fragment(R.layout.fragment_main) {
    private var binding: FragmentMainBinding? = null

    private val iconAdapter by lazy { IconAdapter() }
    private val cardAdapter by lazy { CardAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            iconList.adapter = iconAdapter
            iconList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            iconList.addItemDecoration(IconDecoration(20, 16))

            cardList.adapter = cardAdapter
            cardList.layoutManager = LinearLayoutManager(requireContext())
        }
        cardAdapter.setList(list)
        iconAdapter.setList(iconList)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    companion object {
        private val iconList = listOf(
            Icon.Active(0, "zero"),
            Icon.Disable(1, "first"),
            Icon.Active(2, "second"),
            Icon.Disable(3, "third"),
            Icon.Active(2, "second"),
            Icon.Disable(3, "third"),
            Icon.Active(2, "second"),
            Icon.Disable(3, "third"),
            Icon.Active(2, "second"),
        )

        private val list = listOf(
            Memory(0, "zero", "zero desc", ""),
            Memory(1, "first", "first desc", ""),
            Memory(2, "second", "second desc", ""),
            Memory(3, "third", "third desc", ""),
            Memory(4, "forth", "forth desc", "")
        )
    }
}