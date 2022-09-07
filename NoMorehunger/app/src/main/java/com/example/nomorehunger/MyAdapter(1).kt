package com.example.nomorehunger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nomorehunger.modules.Ngo_List

class MyAdapter(private val Ngo_List: ArrayList<Ngo_List>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ngo_list_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val currentitems = Ngo_List[position]
        holder.name.text = currentitems.name
        holder.phone.text = currentitems.phone
        holder.desc.text = currentitems.desc
    }

    override fun getItemCount(): Int {
        return Ngo_List.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val phone: TextView = itemView.findViewById(R.id.ngo_list_phone)
        val name: TextView = itemView.findViewById(R.id.ngo_list_name)
        val desc: TextView = itemView.findViewById(R.id.ngo_list_desc)




    }

}