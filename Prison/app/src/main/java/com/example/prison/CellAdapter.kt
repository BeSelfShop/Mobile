package com.example.prison

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.CellItem
import models.LogsResponse

class CellAdapter(private val cellList: List<CellItem>) : RecyclerView.Adapter<CellAdapter.CellViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_item,parent,false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val currentItem = cellList[position]

        holder.number.text = currentItem.number
        holder.name.text = currentItem.name



    }

    override fun getItemCount() = cellList.size

    class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val number: TextView = itemView.findViewById(R.id.cell_number)
        val name: TextView = itemView.findViewById(R.id.cell_name)
    }

}