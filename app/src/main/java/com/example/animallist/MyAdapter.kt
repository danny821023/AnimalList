package com.example.animallist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class MyAdapter(var con : Context , var list : List<UsersItem>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    inner class ViewHolder(v:View):RecyclerView.ViewHolder(v)
    {
        var img = v.findViewById<ImageView>(R.id.RV_image)
        var tvId = v.findViewById<TextView>(R.id.RV_id)
        var tvBodytype = v.findViewById<TextView>(R.id.RV_bodytype)
        var tvColor = v.findViewById<TextView>(R.id.RV_color)
        var tvAge = v.findViewById<TextView>(R.id.RV_age)
        var tvSheltername = v.findViewById<TextView>(R.id.RV_sheltername)
        var tvDatefound = v.findViewById<TextView>(R.id.RV_datefound)
        var tvAdressfound = v.findViewById<TextView>(R.id.RV_adressfound)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(con).load(list[position].album_file).into(holder.img)

        holder.tvId.text = list[position].animal_id
        holder.tvBodytype.text = list[position].animal_bodytype
        holder.tvColor.text = list[position].animal_colour
        holder.tvAge.text = list[position].animal_age
        holder.tvSheltername.text = list[position].shelter_name
        holder.tvDatefound.text = list[position].animal_createtime
        holder.tvAdressfound.text = list[position].animal_foundplace

    }

    override fun getItemCount(): Int {
        return list.count()
    }
}
