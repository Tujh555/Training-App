package com.bignerdranch.android.trainingapp.ui.adapters.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class IconDecoration(
    private val edgeSeparation: Int,
    private val allElementsSeparation: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        parent.adapter?.let { adapter ->
            when (parent.getChildAdapterPosition(view)) {
                RecyclerView.NO_POSITION -> {  }
                0 -> {
                    outRect.left = edgeSeparation
                }
                adapter.itemCount - 1 -> {
                    outRect.right = edgeSeparation
                    outRect.left = allElementsSeparation
                }
                else -> {
                    outRect.left = allElementsSeparation
                }
            }
        }
    }
}