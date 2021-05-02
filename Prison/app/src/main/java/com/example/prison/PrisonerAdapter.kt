package com.example.prison

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.PrisonerResponse

class PrisonerAdapter(private val prisonerList: List<PrisonerResponse>, private val listener: OnItemClickListener?=null) : RecyclerView.Adapter<PrisonerAdapter.CellViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_item,parent,false)
        return CellViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        val currentItem = prisonerList?.get(position)

        holder.number.text = currentItem?.id.toString()
        holder.name.text = currentItem?.name
        holder.forname.text = currentItem?.forname
    }

    override fun getItemCount(): Int = prisonerList?.size!!

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