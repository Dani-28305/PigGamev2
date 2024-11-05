package com.example.piggamev2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    private val datos: List<String>,
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        val textView: TextView

        init {
            textView = vista.findViewById(R.id.textView)

            vista.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = adapterPosition

                onItemSelected(datos[adapterPosition])

                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.texto_recycler, viewGroup, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = datos.size


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = datos[position]
        if (position == selectedPosition) {
            viewHolder.itemView.setBackgroundColor(
                ContextCompat.getColor(viewHolder.itemView.context,
                    R.color.seleccion)
            )
        } else {
            viewHolder.itemView.setBackgroundColor(
                ContextCompat.getColor(viewHolder.itemView.context,
                    android.R.color.transparent)
            )
        }
    }
}