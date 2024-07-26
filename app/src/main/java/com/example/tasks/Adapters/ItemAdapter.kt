package com.example.tasks.Adapters

import android.graphics.drawable.Icon
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks.DataClass.ItemDataClass
import com.example.tasks.R
import com.squareup.picasso.Picasso
import java.net.URI
import java.net.URL

class ItemAdapterclass(var datas:List<ItemDataClass>): RecyclerView.Adapter<ItemAdapterclass.ViewHolder>() {



    var a:((Int)->(Unit))?=null
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val image: ImageView = itemView.findViewById(R.id.ImageV)
        val title: TextView = itemView.findViewById(R.id.Title)
        val amount: TextView = itemView.findViewById(R.id.Amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)

        return ItemAdapterclass.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = datas[position]

        Picasso.get().load(Uri.parse(data.image)).into(holder.image);
        holder.title.text = data.title
        holder.amount.text = data.amount
        holder.itemView.setOnClickListener{
            a?.invoke(position)
        }

    }


}