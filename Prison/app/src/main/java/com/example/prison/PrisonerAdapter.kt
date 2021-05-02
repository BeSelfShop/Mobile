package com.example.prison

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.CellItem
import models.PrisonerResponse

class PrisonerAdapter(private val prisonerList: List<PrisonerResponse>) : RecyclerView.Adapter<PrisonerAdapter.CellViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_item,parent,false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val currentItem = prisonerList[position]

        holder.number.text = "Więzień " + currentItem.id.toString()
        holder.name.text = currentItem.name
        holder.forname.text = currentItem.forname
    }

    override fun getItemCount() = prisonerList.size

    class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val number: TextView = itemView.findViewById(R.id.prisoner_number)
        val name: TextView = itemView.findViewById(R.id.prisoner_name)
        val forname: TextView = itemView.findViewById(R.id.prisoner_forname)
    }

}