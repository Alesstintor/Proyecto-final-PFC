package com.ales.fittrackmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.entities.Record

class RecordCustomAdapter(private val data: Array<Record>) :
    RecyclerView.Adapter<RecordCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblTitulo = view.findViewById(R.id.list_item_title) as TextView
        val lblSubtitulo = view.findViewById(R.id.list_item_description) as TextView

        fun bindItem(record: Record){
            lblTitulo.text = record.routine.name
            lblSubtitulo.text = record.date.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_record, parent, false) as LinearLayout
        return ViewHolder(item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = data[position]
        holder.bindItem(record)
    }
    override fun getItemCount() = data.size
}