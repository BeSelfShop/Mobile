package com.example.prison.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prison.R
import models.PrisonersResponse

class PrisonerAdapter(private val prisonersList: List<PrisonersResponse>, private val listener: OnItemClickListener?=null) : RecyclerView.Adapter<PrisonerAdapter.CellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.prisoner_item,parent,false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val currentItem = prisonersList?.get(position)

        holder.number.text = currentItem?.id.toString()
        holder.name.text = currentItem?.name
        holder.forname.text = currentItem?.forname
    }

    override fun getItemCount(): Int = prisonersList?.size!!

    inner class CellViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val number: TextView = itemView.findViewById(R.id.prisoner_number)
        val name: TextView = itemView.findViewById(R.id.prisoner_name)
        val forname: TextView = itemView.findViewById(R.id.prisoner_forname)

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