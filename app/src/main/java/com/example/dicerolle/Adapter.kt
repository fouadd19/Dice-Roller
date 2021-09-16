package com.example.dicerolle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val List:ArrayList<ListItem>):RecyclerView.Adapter<Adapter.myviewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.myviewholder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.listitem,parent,false)
        return myviewholder(itemview)
    }

    override fun onBindViewHolder(holder: Adapter.myviewholder, position: Int) {
      val currentitem = List[position]
        holder.type.setText(currentitem.type.toString())

        var numbers = ""

        for(number in currentitem.numbers){
            numbers+= number.toString() + " "

        }
        holder.Numbers.setText(numbers)
    }

    override fun getItemCount(): Int {
     return List.size
    }

    class myviewholder(itemview:View): RecyclerView.ViewHolder(itemview){
        val type: TextView =  itemview.findViewById(R.id.Type)
        val Numbers: TextView =  itemview.findViewById(R.id.Numbers)

    }

}