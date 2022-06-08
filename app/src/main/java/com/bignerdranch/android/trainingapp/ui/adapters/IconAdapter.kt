package com.bignerdranch.android.trainingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.data.Icon
import com.bignerdranch.android.trainingapp.databinding.IconActiveLayoutBinding
import com.bignerdranch.android.trainingapp.databinding.IconDisableLayoutBinding

class IconAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<Icon>()

    fun setList(list: List<Icon>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {

        return when (items[position]) {
            is Icon.Active -> ViewType.ACTIVE.ordinal
            else -> ViewType.DISABLE.ordinal
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ViewType.ACTIVE.ordinal -> {
                val binding = IconActiveLayoutBinding.inflate(inflater, parent, false)
                IconViewHolder(binding)
            }

            else -> {
                val binding = IconDisableLayoutBinding.inflate(inflater, parent, false)
                IconDisableViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]

        when (holder.itemViewType) {
            ViewType.ACTIVE.ordinal -> (holder as IconViewHolder).bind(item as Icon.Active)
            else -> (holder as IconDisableViewHolder).bind(item as Icon.Disable)
        }
    }

    override fun getItemCount() = items.size

    class IconViewHolder(private val binding: IconActiveLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(icon: Icon.Active) {
            binding.text.text = icon.title
        }
    }

    class IconDisableViewHolder(private val binding: IconDisableLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(icon: Icon.Disable) {
            binding.text.text = icon.title
        }
    }

    enum class ViewType {
        ACTIVE, DISABLE
    }
}