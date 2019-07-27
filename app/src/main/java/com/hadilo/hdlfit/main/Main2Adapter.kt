package com.hadilo.hdlfit.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.DataModel
import com.hadilo.hdlfit.model.Movement

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class Main2Adapter(val context: Context, val listener: (Movement?) -> Unit) : RecyclerView.Adapter<Main2Adapter.ViewHolder>() {

    private var items = mutableListOf<Movement>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_main2, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblMovementName = view.findViewById<TextView>(R.id.lbl_movement_name)
        val lblSetValue = view.findViewById<TextView>(R.id.lbl_set_value)
        val lblRepetitionValue = view.findViewById<TextView>(R.id.lbl_repetition_value)
        val lblLoadValue = view.findViewById<TextView>(R.id.lbl_load_value)

        fun bindItem(items: Movement) {
            lblMovementName.text = items.name
            lblSetValue.text = items.property?.get(0)?.set.toString()
            lblRepetitionValue.text = items.property?.get(0)?.repetition.toString()
            lblLoadValue.text = items.property?.get(0)?.load.toString()
        }
    }

    fun setItems(items: MutableList<Movement>) {
        this.items = items
        notifyDataSetChanged()
    }
}
