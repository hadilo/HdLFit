package com.hadilo.hdlfit.main

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.Movement

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class Main2Adapter(val context: Context, val listener: (Movement?) -> Unit) : RecyclerView.Adapter<Main2Adapter.ViewHolder>() {

    private var items = mutableListOf<Movement>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_main2, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(items[position], listener)

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblMovementName = view.findViewById<TextView>(R.id.lbl_movement_name)
        val lblSetValue = view.findViewById<TextView>(R.id.lbl_set_value)
        val lblRepetitionValue = view.findViewById<TextView>(R.id.lbl_repetition_value)
        val lblLoadValue = view.findViewById<TextView>(R.id.lbl_load_value)

        fun bindItem(items: Movement, listener: (Movement?) -> Unit) {
            lblMovementName.text = items.name
            lblSetValue.text = if(items.property?.isNotEmpty()!!) items.property?.get(0)?.set.toString() else "-"
            lblRepetitionValue.text = if(items.property?.isNotEmpty()!!) items.property?.get(0)?.repetition.toString() else "-"
            lblLoadValue.text = if(items.property?.isNotEmpty()!!) items.property?.get(0)?.load.toString() else "-"

            itemView.setOnClickListener {
                listener(items)
            }
        }
    }

    fun setItems(items: MutableList<Movement>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setItem(items: Movement) {
        this.items.add(items)
        sorting()
        notifyDataSetChanged()
    }

    fun sorting() {
        this.items.sortBy {
            it.name
        }
    }

    fun getItems(): MutableList<Movement> {
        return items
    }
}
