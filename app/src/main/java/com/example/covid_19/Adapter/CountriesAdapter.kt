package com.example.covid_19.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.DetailsActivity
import com.example.covid_19.Model.CountriesModelClass
import com.example.covid_19.R
import com.example.covid_19.ViewHolder.CountriesViewHolder
import com.example.covid_19.databinding.CustomRowBinding
import com.squareup.picasso.Picasso

class CountriesAdapter(private val context: Context,private val arrayList: ArrayList<CountriesModelClass>):RecyclerView.Adapter<CountriesViewHolder>(),Filterable {
    private  var myList=ArrayList<CountriesModelClass>(arrayList)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
       val binding=CustomRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return CountriesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {

        val users=arrayList[position]
        Picasso.get().load(users.Details?.Image).into(holder.customRowBinding.CountryImage)
        holder.customRowBinding.CountryNameText.text=users.Country
        val model:CountriesModelClass
        model=arrayList.get(position)
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,DetailsActivity::class.java).putExtra("Value",model))
        }
    }
    override fun getItemCount(): Int=arrayList.size
    override fun getFilter(): Filter {
        return MyFilter
    }
    private val MyFilter=object:Filter()
    {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val list=ArrayList<CountriesModelClass>()
            if(constraint.toString().isEmpty())
            {
                list.addAll(myList)
            }
            else
            {
                for (obj in myList)
                {
                    if(obj.Country!!.toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        list.add(obj)
                    }
                }
            }
            val result=FilterResults()
            result.values=list
            return result
        }
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            arrayList.clear()
            arrayList.addAll(results!!.values as ArrayList<CountriesModelClass>)
            notifyDataSetChanged()
        }
    }
}