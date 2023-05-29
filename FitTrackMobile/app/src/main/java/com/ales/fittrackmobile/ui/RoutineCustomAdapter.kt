package com.ales.fittrackmobile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.entities.Routine

class RoutineCustomAdapter(private val data: Array<Routine>) :
    RecyclerView.Adapter<RoutineCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val lblTitulo = view.findViewById(R.id.list_item_title) as TextView
        private val lblSubtitulo = view.findViewById(R.id.list_item_description) as TextView

        fun bindItem(routine: Routine){
            lblTitulo.text = routine.name
            lblSubtitulo.text = routine.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as LinearLayout
        return ViewHolder(item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val routine = data[position]
        holder.bindItem(routine)
    }
    override fun getItemCount() = data.size
}