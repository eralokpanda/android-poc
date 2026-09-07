package com.example.androidpoc

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidpoc.databinding.ItemCardBinding

class ItemAdapter(
    private val items: List<Item>
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ItemCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.emojiText.text = item.emoji
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
