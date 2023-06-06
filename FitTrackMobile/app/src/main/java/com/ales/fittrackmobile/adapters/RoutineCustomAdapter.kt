package com.ales.fittrackmobile.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.entities.Routine
import com.ales.fittrackmobile.ui.routine.RoutineActivity

class RoutineCustomAdapter(private val data: Array<Routine>, val context: Context) :
    RecyclerView.Adapter<RoutineCustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val lblTitulo = view.findViewById(R.id.list_item_title) as TextView

        fun bindItem(routine: Routine){
            lblTitulo.text = routine.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_routine, parent, false) as LinearLayout
        return ViewHolder(item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val routine = data[position]
        holder.bindItem(routine)
        holder.itemView.setOnClickListener{openRoutineView(routine)}
    }
    override fun getItemCount() = data.size

    private fun openRoutineView(routine: Routine) {
        val intent = Intent(context, RoutineActivity::class.java)
        intent.putExtra("ROUTINEID", routine.id)
        context.startActivity(intent)
    }
}