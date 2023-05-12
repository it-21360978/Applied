package com.example.job_portal

import Job
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SeekerAdapter(private val SekList:ArrayList<Job>):

    RecyclerView.Adapter<SeekerAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_recieve_job_list, parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSek= SekList[position]
        holder.seeker1.text = currentSek.fullName
        holder.seekerjob.text = currentSek.jobName
        holder.age.text = currentSek.address
    }

    override fun getItemCount(): Int {
        return SekList.size
    }


    class ViewHolder(itemView: View, clickListener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {

        val seeker1: TextView = itemView.findViewById(R.id.seeker1)
        val seekerjob: TextView = itemView.findViewById(R.id.seekerjob)
        val age: TextView = itemView.findViewById(R.id.age)

        init {
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }


    }
}
