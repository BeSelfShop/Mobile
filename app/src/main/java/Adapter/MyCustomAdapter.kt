package Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.beselfshop.R
import com.example.beselfshop.ui.home.Product




class MyCustomAdapter(private val products: List<Product>) : RecyclerView.Adapter<MyCustomAdapter.ListViewHolder>(){

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val curretItem = products[position]

        holder.imageView.setImageResource(curretItem.imageResource)
        holder.productName.text = curretItem.name
        holder.productPrice.text = curretItem.price.toString()
    }

    override fun getItemCount() = products.size
}