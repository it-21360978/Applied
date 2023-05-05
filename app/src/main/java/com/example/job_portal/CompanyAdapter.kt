import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.job_portal.MainActivity
import com.example.job_portal.R
import com.example.job_portal.company
import java.util.*
import kotlin.collections.ArrayList

/*
package com.example.job_portal

import android.location.Location
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CompanyAdapter(private val jobList:ArrayList<company>) : RecyclerView.Adapter<CompanyAdapter.MyViewHolder>(){



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = jobList[position]
        holder.Name.text = currentitem.Name.toString()
        holder.Location.text = currentitem.Location.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_job_list,
       parent,false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

        val Name : TextView = itemView.findViewById(R.id.tvCompany)
        val Location: TextView = itemView.findViewById(R.id.tvlocation)
    }
}
*/
/*package com.example.job_portal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CompanyAdapter(private val jobList: ArrayList<company>) :
    RecyclerView.Adapter<CompanyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_job_list, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jobList[position]
        holder.name.text = currentItem.sfullName
        holder.location.text = currentItem.semail
        //Picasso.get().load(currentItem.cvUrl).into(holder.imageViewjob)
    }

    override fun getItemCount(): Int {
        return jobList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvCompany)
        val location: TextView = itemView.findViewById(R.id.tvlocation)
       // val imageViewjob: ImageView = itemView.findViewById(R.id.imageViewjob)
    }
}*/

class CompanyAdapter(private val jobList: ArrayList<company>) :
    RecyclerView.Adapter<CompanyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_job_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jobList[position]
        holder.name.text = currentItem.CcompanyName
        holder.location.text = currentItem.Ccategory

        // Set click listener for the list item
        holder.itemView.setOnClickListener {
            // Create an Intent to navigate to the DetailsActivity
            val intent = Intent(holder.itemView.context,job_list::class.java)
            // Add the data of the clicked item to the Intent
            intent.putExtra("Ccompany_name", currentItem.CcompanyName)
            intent.putExtra("Ccategory", currentItem.Ccategory)
            intent.putExtra("Ctype", currentItem.Ctype)
            // Add more data fields as needed

            // Start the DetailsActivity with the Intent and pass the data to it
            holder.itemView.context.startActivity(intent)
        }

        // Set click listener for the apply button
        holder.applyBtn.setOnClickListener {
            // Create an Intent to navigate to the ApplyActivity
            val intent = Intent(holder.itemView.context,MainActivity::class.java)
            // Add the data of the clicked item to the Intent(company data class eke declare karapuwata data gannawa)
            intent.putExtra("Ccompany_name", currentItem.CcompanyName)
            intent.putExtra("Ccategory", currentItem.Ccategory)
            intent.putExtra("Ctitle", currentItem.Ctitle)
            intent.putExtra("Ctype",currentItem.Ctype)
            intent.putExtra("Cdescription",currentItem.Cdescription)
            // Add more data fields as needed

            // Start the ApplyActivity with the Intent and pass the data to it
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return jobList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvCompany)
        val location: TextView = itemView.findViewById(R.id.tvlocation)
        val applyBtn: Button = itemView.findViewById(R.id.applybtn)
    }
}



