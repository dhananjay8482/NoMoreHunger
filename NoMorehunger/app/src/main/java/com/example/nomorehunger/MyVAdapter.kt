package com.example.nomorehunger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nomorehunger.modules.Volunteer_List

class MyVAdapter(private val Volunteer_List: ArrayList<Volunteer_List>) : RecyclerView.Adapter<MyVAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.volunteer_list_layout,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyVAdapter.MyViewHolder, position: Int) {
        val currentitems = Volunteer_List[position]
        holder.name.text = currentitems.name
        holder.phone.text = currentitems.phone
        holder.desc.text = currentitems.desc
        holder.address.text = currentitems.address
    }

    override fun getItemCount(): Int {
        return Volunteer_List.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val phone: TextView = itemView.findViewById(R.id.volunteer_list_phone)
        val name: TextView = itemView.findViewById(R.id.volunteer_list_name)
        val desc: TextView = itemView.findViewById(R.id.volunteer_list_desc)
        val address: TextView = itemView.findViewById(R.id.volunteer_list_address)




    }

}