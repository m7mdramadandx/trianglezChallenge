package com.ramadan.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.home.databinding.ItemWordBinding

class RepeatedWordsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: List<Pair<String, Any>>? = null
    fun setItems(_list: List<Pair<String, Any>>) {
        list = emptyList()
        list = _list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(
            ItemWordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        list?.elementAt(position)?.let { (holder as ViewHolder).bind(it) }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    inner class ViewHolder(private val binding: ItemWordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(word: Pair<String, Any>) {
            binding.tvWord.text = word.first
            binding.tvNumber.text = word.second.toString()

            if (layoutPosition == list?.lastIndex)
                binding.spacer.visibility = View.INVISIBLE
        }
    }
}