package com.ramadan.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ramadan.home.databinding.ItemWordBinding

class WordsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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

    inner class ViewHolder(private val itemWordBinding: ItemWordBinding) :
        RecyclerView.ViewHolder(itemWordBinding.root) {
        fun bind(word: Pair<String, Any>) {
            itemWordBinding.tvWord.text = word.first
            itemWordBinding.tvNumber.text = word.second.toString()
        }
    }
}