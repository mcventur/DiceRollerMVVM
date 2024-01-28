package com.mpd.pmdm.dicerollerconstraintlayout.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll

import com.mpd.pmdm.dicerollerconstraintlayout.ui.placeholder.PlaceholderContent.PlaceholderItem
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentItemRollHistoryBinding
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ItemRollHistoryBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRollHistoryRecyclerViewAdapter(
    private val values: List<DiceRoll>
) : RecyclerView.Adapter<MyItemRollHistoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemRollHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.id
        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemRollHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}