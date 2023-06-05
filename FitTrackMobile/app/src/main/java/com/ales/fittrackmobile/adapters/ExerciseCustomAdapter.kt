package com.ales.fittrackmobile.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.ales.fittrackmobile.R
import com.ales.fittrackmobile.entities.Exercise

class ExerciseCustomAdapter(private val data: MutableList<Exercise>) :
    RecyclerView.Adapter<ExerciseCustomAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    private var onDeleteItemClickListener: OnDeleteItemClickListener? = null
    private var deleteButtonVisible: Boolean = false

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val lblTitulo = view.findViewById(R.id.list_item_title) as TextView
        private val lblSubtitulo = view.findViewById(R.id.list_item_description) as TextView
        private val deleteButton = view.findViewById(R.id.delete_button) as AppCompatImageButton

        fun bindItem(exercise: Exercise, deleteButtonVisible: Boolean){
            lblTitulo.text = exercise.name
            lblSubtitulo.text = exercise.description
            showDeleteButton(deleteButtonVisible)
        }

        fun setOnDeleteItem(onDeleteItemClickListener: OnDeleteItemClickListener?) {
            deleteButton.setOnClickListener{
                onDeleteItemClickListener?.onDeleteItemClick(adapterPosition)
            }
        }

        private fun showDeleteButton(show: Boolean) {
            deleteButton.visibility = if (show) View.VISIBLE else View.GONE
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
        holder.bindItem(exercise, deleteButtonVisible)
        holder.itemView.setOnClickListener{
            onItemClickListener?.onItemClick(position)
        }
        holder.setOnDeleteItem(onDeleteItemClickListener)
    }
    override fun getItemCount() = data.size

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setOnDeleteItemClickListener(listener: OnDeleteItemClickListener) {
        onDeleteItemClickListener = listener
    }

    fun setDeleteButtonVisible(visible: Boolean) {
        deleteButtonVisible = visible
    }

    fun updateItems(newItems: List<Exercise>) {
        data.clear()
        data.addAll(newItems)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnDeleteItemClickListener {
        fun onDeleteItemClick(position: Int)
    }

}

