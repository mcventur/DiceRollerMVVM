package com.mpd.pmdm.dicerollerconstraintlayout.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.DiceRoll
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ItemRollHistoryBinding
import java.text.SimpleDateFormat
import java.util.Date

class DiceRollHistoryRecyclerAdapter(
    private var diceRollsList: List<DiceRoll> = emptyList()
) : RecyclerView.Adapter<DiceRollHistoryRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRollHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = diceRollsList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = diceRollsList.size

    inner class ViewHolder(private val binding: ItemRollHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(diceRoll: DiceRoll) {
            binding.idItem.text = diceRoll.id.toString()
            val dateTime = SimpleDateFormat(
                "d/MM/y h:mm a"
            ).format(
                Date(diceRoll.dateTime)
            )
            binding.timeItem.text = dateTime
            binding.side1Item.text = diceRoll.sideDice1.toString()
            binding.side2Item.text = diceRoll.siceDice2.toString()
        }
    }

    /**
     * Actualiza la lista de elementos cargada en el RecyclerView. No muy eficiente
     */
    fun updateList(newDiceRollList: List<DiceRoll>) {
        diceRollsList = newDiceRollList
        notifyDataSetChanged()
    }

}