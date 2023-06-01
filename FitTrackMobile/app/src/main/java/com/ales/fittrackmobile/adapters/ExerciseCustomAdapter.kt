package com.ales.fittrackmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.entities.Exercise

class ExerciseCustomAdapter(private val data: Array<Exercise>) :
    RecyclerView.Adapter<ExerciseCustomAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val lblTitulo = view.findViewById(R.id.list_item_title) as TextView
        val lblSubtitulo = view.findViewById(R.id.list_item_description) as TextView

        fun bindItem(exercise: Exercise){
            lblTitulo.text = exercise.name
            lblSubtitulo.text = exercise.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_exercise, parent, false) as LinearLayout
        return ViewHolder(item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = data[position]
        if (exercise == null) return
        holder.bindItem(exercise)
        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(position)
        }
    }
    override fun getItemCount() = data.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}

