package com.ramadan.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.home.databinding.ItemWordBinding

class Adapter(private val list: Map<String, Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(list.entries.elementAt(position).toPair())
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val itemWordBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemWordBinding.root) {
        fun bind(word: Pair<String, Any>) {
            itemWordBinding.tvWord.text = word.first
            itemWordBinding.tvNumber.text = word.second.toString()
        }
    }
}