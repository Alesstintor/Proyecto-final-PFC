package com.ales.fittrackmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(private val data: Array<HomeActivity.Exercise>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblTitulo = view.findViewById(R.id.list_item_title) as TextView
        val lblSubtitulo = view.findViewById(R.id.list_item_description) as TextView

        fun bindItem(exercise: HomeActivity.Exercise){
            lblTitulo.text = exercise.title
            lblSubtitulo.text = exercise.subtitle
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as LinearLayout
        return ViewHolder(item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = data[position]
        holder.bindItem(exercise)
    }
    override fun getItemCount() = data.size
}