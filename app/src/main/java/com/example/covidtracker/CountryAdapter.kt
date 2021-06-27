package com.example.covidtracker

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.country_item_layout.view.*
import java.lang.NumberFormatException
import java.text.NumberFormat
import kotlin.reflect.typeOf

class CountryAdapter(
       private var list:ArrayList<CountryData> = ArrayList()): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>( ){

    var onItemClick:((country:String)-> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder{
        val itemView =   LayoutInflater.from(parent.context).inflate(
            R.layout.country_item_layout, parent, false
        )

        return CountryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun swapData(list:ArrayList<CountryData>){
        this.list = list
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
holder.bind(list[position])

}







  inner  class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(countryData: CountryData) {
            with(itemView){

                countryCases.setText(NumberFormat.getInstance().format(Integer.parseInt(countryData.cases)))
                countryName.setText(countryData.country)
                sno.text = adapterPosition.toString()
                val img = countryData.countryInfo?.flag
                Picasso.get().load(img).into(countryImage)
                this.setOnClickListener(object :View.OnClickListener{
                    override fun onClick(v: View?) {
                       onItemClick?.invoke(countryData.country!!)
                    }

                })


            }
        }


    }


}