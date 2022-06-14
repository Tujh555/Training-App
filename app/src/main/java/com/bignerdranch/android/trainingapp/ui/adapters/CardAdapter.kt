package com.bignerdranch.android.trainingapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.trainingapp.data.Memory
import com.bignerdranch.android.trainingapp.databinding.CardViewHolderBinding

class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    private val items = mutableListOf<Memory>()

    var cardImageClickListener: CardImageClickListener? = null
    var cardImageLongClickListener: CardImageLongClickListener? = null

    fun setList(list: List<Memory>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return CardViewHolder(CardViewHolderBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class CardViewHolder(private val binding: CardViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(card: Memory) {
                binding.run {
                    title.text = card.title
                    subTitle.text = card.description
                    image.setOnClickListener { cardImageClickListener?.onClick() }
                    image.setOnLongClickListener {
                        cardImageLongClickListener?.onLongClick()
                        true
                    }
                }
            }
    }

    fun interface CardImageClickListener {
        fun onClick()
    }

    fun interface CardImageLongClickListener {
        fun onLongClick()
    }
}