package com.example.job_portal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class jobsAdapter(private val comList:ArrayList<CompanyData>):
           RecyclerView.Adapter<jobsAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener){
        mListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_postjobs_list, parent,false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentCom = comList[position]
        holder.companyname.text = currentCom.CcompanyName
        holder.job1.text = currentCom.Ctitle
        holder.type.text = currentCom.Ctype
    }

        override fun getItemCount(): Int {
            return comList.size
        }


        class ViewHolder(itemView: View, clickListener: onItemClickListener) :
            RecyclerView.ViewHolder(itemView) {

            val companyname: TextView = itemView.findViewById(R.id.companyname)
            val job1: TextView = itemView.findViewById(R.id.job1)
            val type: TextView = itemView.findViewById(R.id.type)

           init {
                itemView.setOnClickListener {
                    clickListener.onItemClick(adapterPosition)
                }
            }


        }
    }





