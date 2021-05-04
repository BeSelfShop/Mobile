package com.example.prison.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.R
import models.Cell

class CellsAdapter(private val cellList: List<Cell>, private val listener: OnItemClickListener?=null) : RecyclerView.Adapter<CellsAdapter.CellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_item,parent,false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val currentItem = cellList?.get(position)

        holder.id.text = currentItem?.id.toString()
        holder.name.text = currentItem?.cellType.cellName
    }

    override fun getItemCount(): Int = cellList?.size!!

    inner class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val id: TextView = itemView.findViewById(R.id.cellId)
        val name: TextView = itemView.findViewById(R.id.cellName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition

            if(position != RecyclerView.NO_POSITION)
            {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}