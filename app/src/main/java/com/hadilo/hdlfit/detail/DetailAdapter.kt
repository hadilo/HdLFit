package com.hadilo.hdlfit.detail

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.hadilo.hdlfit.R
import com.hadilo.hdlfit.model.DataModel
import com.hadilo.hdlfit.model.Movement
import com.hadilo.hdlfit.model.Property

/**
 * Created by Hadilo Muhammad on 2019-07-20.
 */

class DetailAdapter(val context: Context, val listener: (Property?) -> Unit) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private var items = mutableListOf<Property>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_detail, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindItem(items[position])

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblDate = view.findViewById<TextView>(R.id.lbl_date)
        val lblSetValue = view.findViewById<TextView>(R.id.lbl_set_value)
        val lblRepetitionValue = view.findViewById<TextView>(R.id.lbl_repetition_value)
        val lblLoadValue = view.findViewById<TextView>(R.id.lbl_load_value)

        fun bindItem(item: Property) {
            lblMovementName.visibility = View.GONE
            lblSetValue.text = item.set.toString()
            lblRepetitionValue.text = item.created.toString() //if(items.property?.isNotEmpty()!!) items.property?.get(0)?.repetition.toString() else "-"
            lblLoadValue.text = item.updated.toString() //if(items.property?.isNotEmpty()!!) items.property?.get(0)?.load.toString() else "-"
        }
    }

    fun setItems(items: MutableList<Property>?) {
        if (items != null) {
            this.items.addAll(items)
        }
        notifyDataSetChanged()
    }

    fun setItem(items: Property) {
        this.items.add(items)
//        sorting()
        notifyDataSetChanged()
    }

//    fun sorting() {
//        this.items.sortBy {
//            it.name
//        }
//    }

    fun getItems(): MutableList<Property> {
        return items
    }
}
