package com.example.prison

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.LogsResponse

class LogsAdapter(private val logsList: List<LogsResponse>) : RecyclerView.Adapter<LogsAdapter.LogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogsViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.logs_item,parent,false)
        return LogsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LogsViewHolder, position: Int) {
        val currentItem = logsList[position]

        holder.number.text = "Wydarzenie " + currentItem.id.toString()
        holder.cont.text = currentItem.controller
        holder.what.text = currentItem.action
        holder.data.text = currentItem.logData
    }

    override fun getItemCount() = logsList.size

    class LogsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val number: TextView = itemView.findViewById(R.id.logs_number)
        val cont: TextView = itemView.findViewById(R.id.logs_cont)
        val what: TextView = itemView.findViewById(R.id.logs_what)
        val data: TextView = itemView.findViewById(R.id.logs_data)
    }

}